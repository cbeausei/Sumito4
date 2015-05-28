package axelindustry.sumito4.IA;

import android.util.Log;

import java.util.LinkedList;

/**
 * Created by Clement on 08/04/2015.
 */
public class Board {

    private int[][] matrice;
    private Move[] userMove;
    private int colorTemp;

    public Board() {
        matrice=new int[9][9];
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                matrice[i][j]=-2;
            }
        }
        for(int j=4;j<9;j++)
            matrice[0][j]=0;
        for(int j=3;j<9;j++)
            matrice[1][j]=0;
        for(int j=2;j<9;j++)
            matrice[2][j]=-1;
        for(int j=4;j<7;j++)
            matrice[2][j]=0;
        for(int j=1;j<9;j++)
            matrice[3][j]=-1;
        for(int j=0;j<9;j++)
            matrice[4][j]=-1;
        for(int j=0;j<8;j++)
            matrice[5][j]=-1;
        for(int j=0;j<7;j++)
            matrice[6][j]=-1;
        for(int j=2;j<5;j++)
            matrice[6][j]=1;
        for(int j=0;j<6;j++)
            matrice[7][j]=1;
        for(int j=0;j<5;j++)
            matrice[8][j]=1;
    }

    public Board(Board board) {
        matrice=new int[9][9];
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                this.matrice[i][j]=board.get(i,j);
            }
        }
    }

    public int get(int i,int j) {
        if (exist(i,j)) return matrice[i][j];
        else return(-3);
    }
/*
    private String convert(int n) {
        if(n==1) return "*";
        if(n==0) return "^";
        if(n==-1) return "°";
        return "";
    }

    public void display() {
        for(int i=0;i<4;i++)
            Log.d("test"," ");
        for(int i=4;i<9;i++) {
            Log.d("test",convert(matrice[0][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        for(int i=0;i<3;i++)
            Log.d("test"," ");
        for(int i=3;i<9;i++) {
            Log.d("test",convert(matrice[1][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        for(int i=0;i<2;i++)
            Log.d("test"," ");
        for(int i=2;i<9;i++) {
            Log.d("test",convert(matrice[2][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        for(int i=0;i<1;i++)
            Log.d("test"," ");
        for(int i=1;i<9;i++) {
            Log.d("test",convert(matrice[3][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        for(int i=0;i<9;i++) {
            Log.d("test",convert(matrice[4][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        for(int i=0;i<1;i++)
            Log.d("test"," ");
        for(int i=0;i<8;i++) {
            Log.d("test",convert(matrice[5][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        for(int i=0;i<2;i++)
            Log.d("test"," ");
        for(int i=0;i<7;i++) {
            Log.d("test",convert(matrice[6][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        for(int i=0;i<3;i++)
            Log.d("test"," ");
        for(int i=0;i<6;i++) {
            Log.d("test",convert(matrice[7][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        for(int i=0;i<4;i++)
            Log.d("test"," ");
        for(int i=0;i<5;i++) {
            Log.d("test",convert(matrice[8][i]));
            Log.d("test"," ");
        }
        Log.d("test","\n");
        Log.d("test","\n");
    }
*/
    public Boolean exist(int i,int j) {
        if ((i>=0)&&(i<9)&&(j>=0)&&(j<9)) {
            return (matrice[i][j] != -2);
        }
        return false;
    }

    public Boolean isPlayer(int i,int j,int iaColor) {
        if (exist(i,j)) {
            return (matrice[i][j]==iaColor);
        }
        return false;
    }

    public void doMove(MoveWay moveWay) {
        int type=moveWay.type;
        int a=moveWay.a;
        int b=moveWay.b;
        int x=moveWay.x;
        int y=moveWay.y;
        if (type==0) {
            matrice[a][b]=matrice[x][y];
            matrice[x][y]=-1;
            return;
        }
        if (type==1) {
            matrice[a][b]=-1;
        }
    }

    public void doMoveList(MoveWayList moveWayList) {
        if (moveWayList==null) return;
        doMove(moveWayList.getMoveWay());
        doMoveList(moveWayList.getNext());
    }

    public LinkedList<Ball> getBalls() {
        LinkedList<Ball> ballList=new LinkedList<>();
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if (matrice[i][j]>=0) {
                    ballList.add(new Ball(matrice[i][j],i,j));
                }
            }
        }
        return ballList;
    }

    public Boolean[] getDirections(int i,int j) {
        userMove=new Move[6];
        Boolean [] list=new Boolean[6];
        int color=matrice[i][j];
        colorTemp=color;
        Bot bot=new Bot(0,0,0,this);
        int[] i0={0,-1,-1,0,1,1};
        int[] j0={1,1,0,-1,-1,0};
        for(int k=0;k<6;k++) {
            Move move=new Move(i,j,1,0,0,i0[k],j0[k]);
            list[k]=bot.isPossible(color,move);
            userMove[k]=move;
        }
        return list;
    }

    public Boolean[] getDirections(int i1,int j1,int i2,int j2) {
        userMove=new Move[6];
        Boolean [] list=new Boolean[6];
        int color=matrice[i1][j1];
        colorTemp=color;
        Bot bot=new Bot(0,0,0,this);
        int x=i2-i1;
        int y=j2-j1;
        int[] i0={0,-1,-1,0,1,1};
        int[] j0={1,1,0,-1,-1,0};
        for(int k=0;k<6;k++) {
            Move move=new Move(i1,j1,2,x,y,i0[k],j0[k]);
            list[k]=bot.isPossible(color,move);
            userMove[k]=move;
        }
        return list;
    }

    public Boolean[] getDirections(int i1,int j1,int i2,int j2,int i3,int j3) {
        userMove=new Move[6];
        Boolean [] list=new Boolean[6];
        int color=matrice[i1][j1];
        colorTemp=color;
        Bot bot=new Bot(0,0,0,this);
        int x;
        int y;
        int i;
        int j;
        if ((i2-i1==i3-i2)&&(j2-j1==j3-j2)) {
            i=i1;
            j=j1;
            x=i2-i1;
            y=j2-j1;
        }
        else if ((i3-i1==i2-i3)&&(j3-j1==j2-j3)) {
            i=i1;
            j=j1;
            x=i3-i1;
            y=j3-j1;
        }
        else {
            i=i2;
            j=j2;
            x=i1-i2;
            y=j1-j2;
        }
        int[] i0={0,-1,-1,0,1,1};
        int[] j0={1,1,0,-1,-1,0};
        for(int k=0;k<6;k++) {
            Move move=new Move(i,j,3,x,y,i0[k],j0[k]);
            list[k]=bot.isPossible(color,move);
            userMove[k]=move;
        }
        return list;
    }

    private int abs(int x) {
        if (x>=0) return x;
        else return(-x);
    }

    private int maxAbs(int x,int y) {
        if (abs(x)>=abs(y)) return(abs(x));
        else return(abs(y));
    }

    public LinkedList<BallMove> differences(Board board1,Board board2) {
        LinkedList<BallMove> ballMoveList=new LinkedList<>();
        int iStart=0;
        int jStart=0;
        int iOther=0;
        int jOther=0;
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if (board1.get(i,j)!=board2.get(i,j)) {
                    if (board2.get(i,j)==-1) {
                        iStart=i;
                        jStart=j;
                    }
                    if (board2.get(i,j)!=-1) {
                        iOther=i;
                        jOther=j;
                    }
                }
            }
        }
        int i=iOther-iStart;
        int j=jOther-jStart;
        int a=maxAbs(i,j);
        i=i/a;
        j=j/a;
        while(board1.get(iStart,jStart)>=0) {
            ballMoveList.add(new BallMove(iStart,jStart,iStart+i,jStart+j));
            iStart+=i;
            jStart+=j;
        }
        return ballMoveList;
    }

    public LinkedList<BallMove> doUserMove(int angle) {
        Move move=userMove[angle];
        Bot bot=new Bot(0,0,0,this);
        if (bot.isPossible(colorTemp,move)) {
            MoveWayList moveWayList=bot.getPossibles(colorTemp).getMoveWayList();
            Board boardTemp=new Board(this);
            doMoveList(moveWayList);
            return differences(boardTemp,this);
        }
        return new LinkedList<>();
    }

    private void initiate() {
        matrice=new int[9][9];
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                matrice[i][j]=-2;
            }
        }
        for(int j=4;j<9;j++)
            matrice[0][j]=-1;
        for(int j=3;j<9;j++)
            matrice[1][j]=-1;
        for(int j=2;j<9;j++)
            matrice[2][j]=-1;
        for(int j=1;j<9;j++)
            matrice[3][j]=-1;
        for(int j=0;j<9;j++)
            matrice[4][j]=-1;
        for(int j=0;j<8;j++)
            matrice[5][j]=-1;
        for(int j=0;j<7;j++)
            matrice[6][j]=-1;
        for(int j=0;j<6;j++)
            matrice[7][j]=-1;
        for(int j=0;j<5;j++)
            matrice[8][j]=-1;
    }

    private void daisy() {
        initiate();
        matrice[0][4]=1;
        matrice[0][5]=1;
        matrice[0][7]=0;
        matrice[0][8]=0;
        matrice[1][3]=1;
        matrice[1][4]=1;
        matrice[1][5]=1;
        matrice[1][6]=0;
        matrice[1][7]=0;
        matrice[1][8]=0;
        matrice[2][3]=1;
        matrice[2][4]=1;
        matrice[2][6]=0;
        matrice[2][7]=0;
        matrice[6][1]=0;
        matrice[6][2]=0;
        matrice[6][4]=1;
        matrice[6][5]=1;
        matrice[7][0]=0;
        matrice[7][1]=0;
        matrice[7][2]=0;
        matrice[7][3]=1;
        matrice[7][4]=1;
        matrice[7][5]=1;
        matrice[8][0]=0;
        matrice[8][1]=0;
        matrice[8][3]=1;
        matrice[8][4]=1;
    }

    private void alien() {
        initiate();
        matrice[0][4]=0;
        matrice[0][6]=0;
        matrice[0][8]=0;
        matrice[1][4]=0;
        matrice[1][5]=1;
        matrice[1][6]=1;
        matrice[1][7]=0;
        matrice[2][3]=0;
        matrice[2][4]=1;
        matrice[2][5]=0;
        matrice[2][6]=1;
        matrice[2][7]=0;
        matrice[3][4]=0;
        matrice[3][5]=0;
        matrice[5][3]=1;
        matrice[5][4]=1;
        matrice[6][1]=1;
        matrice[6][2]=0;
        matrice[6][3]=1;
        matrice[6][4]=0;
        matrice[6][5]=1;
        matrice[7][1]=1;
        matrice[7][2]=0;
        matrice[7][3]=0;
        matrice[7][4]=1;
        matrice[8][0]=1;
        matrice[8][2]=1;
        matrice[8][4]=1;
    }

    private void domination() {
        initiate();
        matrice[1][3]=1;
        matrice[1][8]=0;
        matrice[2][2]=1;
        matrice[2][3]=1;
        matrice[2][7]=0;
        matrice[2][8]=0;
        matrice[3][1]=1;
        matrice[3][2]=1;
        matrice[3][3]=1;
        matrice[3][4]=1;
        matrice[3][6]=0;
        matrice[3][7]=0;
        matrice[3][8]=0;
        matrice[4][3]=0;
        matrice[4][5]=0;
        matrice[5][0]=0;
        matrice[5][1]=0;
        matrice[5][2]=0;
        matrice[5][4]=1;
        matrice[5][5]=1;
        matrice[5][6]=1;
        matrice[5][7]=1;
        matrice[6][0]=0;
        matrice[6][1]=0;
        matrice[6][5]=1;
        matrice[6][6]=1;
        matrice[7][0]=0;
        matrice[7][5]=1;
    }

    private void infiltration() {
        initiate();
        matrice[0][5]=0;
        matrice[0][6]=1;
        matrice[0][7]=0;
        matrice[1][4]=0;
        matrice[1][5]=0;
        matrice[1][6]=0;
        matrice[1][7]=0;
        matrice[2][3]=0;
        matrice[2][4]=1;
        matrice[2][5]=0;
        matrice[2][6]=1;
        matrice[2][7]=0;
        matrice[3][2]=0;
        matrice[3][7]=0;
        matrice[5][1]=1;
        matrice[5][6]=1;
        matrice[6][1]=1;
        matrice[6][2]=0;
        matrice[6][3]=1;
        matrice[6][4]=0;
        matrice[6][5]=1;
        matrice[7][1]=1;
        matrice[7][2]=1;
        matrice[7][3]=1;
        matrice[7][4]=1;
        matrice[8][1]=1;
        matrice[8][2]=0;
        matrice[8][3]=1;
    }

    private void wall() {
        initiate();
        matrice[0][6]=1;
        matrice[2][3]=1;
        matrice[2][4]=1;
        matrice[2][5]=1;
        matrice[2][6]=1;
        matrice[2][7]=1;
        matrice[3][1]=1;
        matrice[3][2]=1;
        matrice[3][3]=1;
        matrice[3][4]=1;
        matrice[3][5]=1;
        matrice[3][6]=1;
        matrice[3][7]=1;
        matrice[3][8]=1;
        matrice[5][0]=0;
        matrice[5][1]=0;
        matrice[5][2]=0;
        matrice[5][3]=0;
        matrice[5][4]=0;
        matrice[5][5]=0;
        matrice[5][6]=0;
        matrice[5][7]=0;
        matrice[6][1]=0;
        matrice[6][2]=0;
        matrice[6][3]=0;
        matrice[6][4]=0;
        matrice[6][5]=0;
        matrice[8][2]=0;
    }

    private void fujiyama() {
        initiate();
        matrice[0][4]=1;
        matrice[0][5]=1;
        matrice[0][6]=1;
        matrice[0][7]=1;
        matrice[0][8]=1;
        matrice[1][4]=1;
        matrice[1][5]=0;
        matrice[1][6]=0;
        matrice[1][7]=1;
        matrice[2][4]=1;
        matrice[2][5]=0;
        matrice[2][6]=1;
        matrice[3][4]=1;
        matrice[3][5]=1;
        matrice[5][3]=0;
        matrice[5][4]=0;
        matrice[6][2]=0;
        matrice[6][3]=1;
        matrice[6][4]=0;
        matrice[7][1]=0;
        matrice[7][2]=1;
        matrice[7][3]=1;
        matrice[7][4]=0;
        matrice[8][0]=0;
        matrice[8][1]=0;
        matrice[8][2]=0;
        matrice[8][3]=0;
        matrice[8][4]=0;
    }

    private void snakes() {
        initiate();
        matrice[0][4]=0;
        matrice[0][5]=0;
        matrice[0][6]=0;
        matrice[0][7]=0;
        matrice[0][8]=0;
        matrice[1][3]=0;
        matrice[2][2]=0;
        matrice[3][1]=0;
        matrice[4][1]=0;
        matrice[5][1]=0;
        matrice[5][2]=0;
        matrice[4][3]=0;
        matrice[3][4]=0;
        matrice[3][5]=0;
        matrice[5][3]=1;
        matrice[5][4]=1;
        matrice[4][5]=1;
        matrice[3][6]=1;
        matrice[3][7]=1;
        matrice[4][7]=1;
        matrice[5][7]=1;
        matrice[6][6]=1;
        matrice[7][5]=1;
        matrice[8][4]=1;
        matrice[8][3]=1;
        matrice[8][2]=1;
        matrice[8][1]=1;
        matrice[8][0]=1;
    }

    private void checkerboard() {
        initiate();
        matrice[1][3]=0;
        matrice[1][4]=1;
        matrice[1][5]=0;
        matrice[1][6]=1;
        matrice[1][7]=0;
        matrice[1][8]=1;
        matrice[3][1]=1;
        matrice[3][2]=0;
        matrice[3][3]=1;
        matrice[3][4]=0;
        matrice[3][5]=1;
        matrice[3][6]=0;
        matrice[3][7]=1;
        matrice[3][8]=0;
        matrice[5][0]=0;
        matrice[5][1]=1;
        matrice[5][2]=0;
        matrice[5][3]=1;
        matrice[5][4]=0;
        matrice[5][5]=1;
        matrice[5][6]=0;
        matrice[5][7]=1;
        matrice[7][0]=1;
        matrice[7][1]=0;
        matrice[7][2]=1;
        matrice[7][3]=0;
        matrice[7][4]=1;
        matrice[7][5]=0;
    }

    public int[] getSize(){
        int [] size={0,0};
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(this.matrice[i][j]==0) {
                    size[0]+=1;
                }
                else if ( this.matrice[i][j]==1){
                    size[1]+=1;
                }
            }
        }
        return size;
    }


//Challenges


    public void initiateChallenge(int number) {
        if (number==1) daisy();
        if (number==2) alien();
        if (number==3) domination();
        if (number==4) infiltration();
        if (number==5) wall();
        if (number==6) fujiyama();
        if (number==7) snakes();
        if (number==8) checkerboard();
        //tutoriel
        if (number==9) position1();
    }
//Tutoriel


    public void position1(){
        initiate();
        matrice[4][2]=1;
        matrice[4][3]=1;
        matrice[4][4]=1;

    }


}
