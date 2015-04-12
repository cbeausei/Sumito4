package axelindustry.sumito4.IA;

/**
 * Created by Clement on 08/04/2015.
 */
public class MoveWay {
    public int type; // 0=deplacement, 1=suppression
    public int x;
    public int y;
    public int a;
    public int b;

    public MoveWay(int type,int x,int y,int a,int b) {
        this.type=type;
        this.x=x;
        this.y=y;
        this.a=a;
        this.b=b;
    }

    public MoveWay() {
        type=0;
        x=0;
        y=0;
        a=0;
        b=0;
    }

    public void display() {
        System.out.println(""+type+" "+x+" "+y+" "+a+" "+b);
    }

    public Boolean isEqual(MoveWay moveWay) {
        return((type==moveWay.type)&(x==moveWay.x)&(y==moveWay.y)&(a==moveWay.a)&(b==moveWay.b));
    }
}
