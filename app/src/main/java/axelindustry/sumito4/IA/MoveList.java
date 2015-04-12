package axelindustry.sumito4.IA;

/**
 * Created by Clement on 08/04/2015.
 */
public class MoveList {
    private Move move;
    private MoveWayList moveWayList;
    private MoveList next;


    public MoveList(Move move,MoveWayList moveWayList) {
        this.move=move;
        this.moveWayList=moveWayList;
        this.next=null;
    }

    public void addMove(Move move,MoveWayList moveWayList) {
        if ((this.move.isEqual(move))&(this.moveWayList.isEqual(moveWayList))) {
            return;
        }
        if (next==null) {
            next=new MoveList(move,moveWayList);
            return;
        }
        next.addMove(move,moveWayList);
        return;
    }

    public MoveWayList getMoveWayList() {
        return moveWayList;
    }

    public MoveList getNext() {
        return next;
    }

    public void display() {
        moveWayList.display();
        if (next!=null) next.display();
    }
}
