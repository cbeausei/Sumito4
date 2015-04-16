package axelindustry.sumito4.IA;

import java.util.LinkedList;

/**
 * Created by Clement on 09/04/2015.
 */
public class MoveWayList {
    private MoveWay moveWay;
    private MoveWayList next;

    public MoveWayList() {
        moveWay=new MoveWay();
        next=null;
    }

    public MoveWayList(MoveWay moveWay) {
        this.moveWay=moveWay;
        this.next=null;
    }

    public void addMoveWay(MoveWay moveWay) {
        if (next==null) {
            next=new MoveWayList(moveWay);
            return;
        }
        next.addMoveWay(moveWay);
        return;
    }

    public MoveWay getMoveWay() {
        return moveWay;
    }

    public MoveWayList getNext() {
        return next;
    }

    public Boolean isEqual(MoveWayList moveWayList) {
        if (moveWayList==null) return true;
        if (next==null) return ((moveWay.isEqual(moveWayList.getMoveWay()))&&(moveWayList.getNext()==null));
        return((moveWay.isEqual(moveWayList.getMoveWay()))&&next.isEqual(moveWayList.getNext()));
    }

    public void display() {
        moveWay.display();
        if (next!=null) next.display();
        if (next==null) System.out.println();
    }

    public void getBallMoved(LinkedList<BallMove> list) {
        moveWay.getBallMoved(list);
        if (next!=null) {
            next.getBallMoved(list);
        }
    }
}
