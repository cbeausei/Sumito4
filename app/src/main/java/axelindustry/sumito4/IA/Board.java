package axelindustry.sumito4.IA;

import java.util.LinkedList;

/**
 * Created by Clement on 08/04/2015.
 */
public class Board {

    private int[][] matrice;

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
        return matrice[i][j];
    }

    private String convert(int n) {
        if(n==1) return "*";
        if(n==0) return "^";
        if(n==-1) return "°";
        return "";
    }

    public void display() {
        for(int i=0;i<4;i++)
            System.out.print(" ");
        for(int i=4;i<9;i++) {
            System.out.print(convert(matrice[0][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<3;i++)
            System.out.print(" ");
        for(int i=3;i<9;i++) {
            System.out.print(convert(matrice[1][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<2;i++)
            System.out.print(" ");
        for(int i=2;i<9;i++) {
            System.out.print(convert(matrice[2][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<1;i++)
            System.out.print(" ");
        for(int i=1;i<9;i++) {
            System.out.print(convert(matrice[3][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<9;i++) {
            System.out.print(convert(matrice[4][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<1;i++)
            System.out.print(" ");
        for(int i=0;i<8;i++) {
            System.out.print(convert(matrice[5][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<2;i++)
            System.out.print(" ");
        for(int i=0;i<7;i++) {
            System.out.print(convert(matrice[6][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<3;i++)
            System.out.print(" ");
        for(int i=0;i<6;i++) {
            System.out.print(convert(matrice[7][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        for(int i=0;i<4;i++)
            System.out.print(" ");
        for(int i=0;i<5;i++) {
            System.out.print(convert(matrice[8][i]));
            System.out.print(" ");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    public Boolean exist(int i,int j) {
        if ((i>=0)&(i<9)&(j>=0)&(j<9)) {
            return (matrice[i][j]!=-2);
        }
        return false;
    }

    public Boolean isPlayer(int i,int j,int iaColor) {
        if (exist(i,j)) {
            return matrice[i][j]==iaColor;
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
            return;
        }
    }

    public void doMoveList(MoveWayList moveWayList) {
        if (moveWayList==null) return;
        doMove(moveWayList.getMoveWay());
        doMoveList(moveWayList.getNext());
        return;
    }

    public LinkedList<Bowl> getBowls() {
        LinkedList<Bowl> bowlList=new LinkedList<Bowl>();
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if (matrice[i][j]>=0) {
                    bowlList.add(new Bowl(matrice[i][j],i,j));
                }
            }
        }
        return bowlList;
    }
}
