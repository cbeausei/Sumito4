package axelindustry.sumito4.IA;

import java.util.LinkedList;

/**
 * Created by Clement on 09/04/2015.
 */
public class Bot {
    private Board board;
    private MoveWayList moveWayList;
    private int difficulty;
    private int aggressivity;
    //private int seuil=-1;
    private MoveList[] possibles;
    private int iaColor;
    private int[][] aggressivityTable={{0, 0, 0, 0, 13, 13, 13, 13, 13},
            {0, 0, 0, 13, 14, 14, 14, 14, 13},
            {0, 0, 13, 14, 15, 15, 15, 14, 13},
            {0, 13, 14, 15, 16, 16, 15, 14, 13},
            {13, 14, 15, 16, 17, 16, 15, 14, 13},
            {13, 14, 15, 16, 16, 15, 14, 13, 0},
            {13, 14, 15, 15, 15, 14, 13, 0, 0},
            {13, 14, 14, 14, 14, 13, 0, 0, 0},
            {13, 13, 13, 13, 13, 0, 0, 0, 0}};

    public Bot(int iaColor,int difficulty,int aggressivity,Board board) {
        this.board=board;
        this.difficulty=difficulty;
        this.aggressivity=aggressivity;
        possibles=new MoveList[2];
        possibles[0]=null;
        possibles[1]=null;
        this.iaColor=iaColor;
        moveWayList=null;
    }

    private int sum(int color,Board board) {
        int s=0;
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if (board.isPlayer(i,j,color)) {
                    s+=aggressivityTable[i][j];
                }
                else if (board.isPlayer(i,j,1-color)){
                    s-=aggressivityTable[i][j]*aggressivity;
                }
            }
        }
        return s;
    }

    private MoveWayList bestMove(int color,Board board) {
        int valMax=-10000;
        MoveWayList moveWayList=new MoveWayList();
        while (possibles[color]!=null) {
            MoveWayList moveWayListToCompare=possibles[color].getMoveWayList();
            possibles[color]=possibles[color].getNext();
            Board boardToTest=new Board(board);
            boardToTest.doMoveList(moveWayListToCompare);
            int valTemp=sum(color,boardToTest);
            if (valTemp>valMax) {
                valMax=valTemp;
                moveWayList=moveWayListToCompare;
            }
        }
        return moveWayList;
    }

    private Move normalize(Move move) {
        Move moveNormalized=new Move(move);
        if(moveNormalized.n==1) {
            moveNormalized.u=0;
            moveNormalized.v=0;
        }
        int n=moveNormalized.n;
        int u=moveNormalized.u;
        int v=moveNormalized.v;
        if((n>1) & ( ((u!=0)|(v!=1)) & ((u!=-1)|(v!=1)) & ((u!=1)|(v!=0)) )) {
            moveNormalized.i+=n*u;
            moveNormalized.j+=n*v;
            moveNormalized.u=v;
            moveNormalized.v=u;
        }
        return(moveNormalized);
    }

    private void addMove(Move move,int color,MoveWayList moveWayList) {
        if (possibles[color]==null) {
            possibles[color]=new MoveList(move,moveWayList);
            return;
        }
        possibles[color].addMove(move,moveWayList);
    }

    private void addMoveWay(MoveWay moveWay) {
        if (moveWayList==null) {
            moveWayList=new MoveWayList(moveWay);
            return;
        }
        moveWayList.addMoveWay(moveWay);
    }

    public Boolean isPossible(int color,Move move) {
        if ((move.n<1)|(move.n>3)) return false;
        move=normalize(move);
        int i=move.i;
        int j=move.j;
        int n=move.n;
        int u=move.u;
        int v=move.v;
        int x=move.x;
        int y=move.y;
        if ((n>1) & ( ((u!=0)|(v!=1)) & ((u!=-1)|(v!=1)) & ((u!=1)|(v!=0)) )) {
            System.out.println("Error in normalization");
            return false;
        }
        for(int k=0;k<n;k++) {
            int a=i+k*u;
            int b=j+k*v;
            if (!((board.exist(a,b))&(board.isPlayer(a,b,color)))) {
                return false;
            }
            // We now know that the bloc exists and belongs to color
        }
        if (n==1) {
            int a=i+x;
            int b=j+y;
            if ((board.exist(a,b)&board.isPlayer(a,b,-1))) {
                MoveWay moveWay=new MoveWay(0,i,j,a,b);
                moveWayList=null;
                addMoveWay(moveWay);
                addMove(move,color,moveWayList);
                return true;
            }
            else return false;
        }
        int a;
        int b;
        int k;
        int l;
        if (((u==x)&(v==y))||((u==-x)&(v==-y))) {
            if ((u==x)&(v==y)) {
                a=i+n*u;
                b=j+n*v;
                k=i;
                l=j;
            }
            else {
                a=i+x;
                b=j+y;
                k=i+(n-1)*u;
                l=j+(n-1)*v;
            }
            if (!board.exist(a, b)) return false;
            if (board.isPlayer(a,b,-1)) {
                moveWayList=null;
                MoveWay moveWay=new MoveWay(0,k,l,a,b);
                addMoveWay(moveWay);
                addMove(move,color,moveWayList);
                return true;
            }
            if (board.isPlayer(a, b, 1-color)) {
                if (!board.exist(a+x,b+y)) {
                    moveWayList=null;
                    MoveWay moveWay1=new MoveWay(1,k,l,a,b);
                    MoveWay moveWay2=new MoveWay(0,k,l,a,b);
                    addMoveWay(moveWay1);
                    addMoveWay(moveWay2);
                    addMove(move,color,moveWayList);
                    return true;
                }
                if (board.isPlayer(a+x,b+y,color)) {
                    return false;
                }
                if (board.isPlayer(a+x,b+y,-1)) {
                    moveWayList=null;
                    MoveWay moveWay1=new MoveWay(0,a,b,a+x,b+y);
                    MoveWay moveWay2=new MoveWay(0,k,l,a,b);
                    addMoveWay(moveWay1);
                    addMoveWay(moveWay2);
                    addMove(move,color,moveWayList);
                    return true;
                }
                if (n==2) return false;
                if (!board.exist(a+2*x,b+2*y)) {
                    moveWayList=null;
                    MoveWay moveWay1=new MoveWay(1,k,l,a,b);
                    MoveWay moveWay2=new MoveWay(0,k,l,a,b);
                    addMoveWay(moveWay1);
                    addMoveWay(moveWay2);
                    addMove(move,color,moveWayList);
                    return true;
                }
                if (board.isPlayer(a+2*x,b+2*y,-1)) {
                    moveWayList=null;
                    MoveWay moveWay1=new MoveWay(0,a,b,a+2*x,b+2*y);
                    MoveWay moveWay2=new MoveWay(0,k,l,a,b);
                    addMoveWay(moveWay1);
                    addMoveWay(moveWay2);
                    addMove(move,color,moveWayList);
                    return true;
                }
                return false;
            }
            return false;
        }
        moveWayList=null;
        for(int m=0;m<n;m++) {
            a=i+m*u+x;
            b=j+m*v+y;
            addMoveWay(new MoveWay(0,a-x,b-y,a,b));
            if (!( (board.exist(a,b)) & (board.isPlayer(a,b,-1)) )) {
                return false;
            }
        }
        addMove(move,color,moveWayList);
        return true;
    }

    private void findPossibles(int color) {
        possibles[color]=null;
        int[][] directions={{1, 0}, {0, 1}, {-1, 1}};
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(board.isPlayer(i,j,color)) {
                    for(int n=1;n<4;n++) {
                        for(int a=0;a<3;a++) {
                            int u=directions[a][0];
                            int v=directions[a][1];
                            for(int b=0;b<3;b++) {
                                int x=directions[b][0];
                                int y=directions[b][1];
                                Move move1=new Move(i,j,n,u,v,x,y);
                                Move move2=new Move(i,j,n,u,v,-x,-y);
                                isPossible(color,move1);
                                isPossible(color,move2);							}
                        }
                    }
                }
            }
        }
    }

    public LinkedList<BallMove> play(Board board) {
        this.board=board;
        if (difficulty==0) {
            findPossibles(iaColor);
            //possibles[iaColor].display();
            MoveWayList moveWayList=bestMove(iaColor,board);
            board.doMoveList(moveWayList);
            LinkedList<BallMove> list=new LinkedList<>();
            moveWayList.getBallMoved(list);
            return(list);
        }
        return(new LinkedList<>());
    }
}
