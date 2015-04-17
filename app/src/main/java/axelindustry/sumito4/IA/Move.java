package axelindustry.sumito4.IA;

/**
 * Created by Clement on 08/04/2015.
 */
public class Move {
    public int i;
    public int j;
    public int n;
    public int u;
    public int v;
    public int x;
    public int y;

    public Move(int i,int j,int n,int u,int v,int x,int y) {
        this.i=i;
        this.j=j;
        this.n=n;
        this.u=u;
        this.v=v;
        this.x=x;
        this.y=y;
    }

    public Move() {
        this.i=0;
        this.j=0;
        this.n=0;
        this.u=0;
        this.v=0;
        this.x=0;
        this.y=0;
    }

    public Move(Move move) {
        this.i=move.i;
        this.j=move.j;
        this.n=move.n;
        this.u=move.u;
        this.v=move.v;
        this.x=move.x;
        this.y=move.y;
    }

    public Boolean isEqual(Move move) {
        if ((i==move.i)&&(j==move.j)&&(n==move.n)&&(u==move.u)&&(v==move.v)&&(x==move.x)&&(y==move.y)) {
            return true;
        }
        else return false;
    }
}
