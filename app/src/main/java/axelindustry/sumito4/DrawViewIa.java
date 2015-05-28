package axelindustry.sumito4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

import axelindustry.sumito4.IA.Ball;
import axelindustry.sumito4.IA.BallMove;
import axelindustry.sumito4.IA.Board;
import axelindustry.sumito4.IA.Bot;

import static java.lang.Thread.sleep;

/**
 * Created by axel on 10/05/15.
 */




public class DrawViewIa extends DrawView {


    private Boolean ia;
    private Bot bot1;
    private Bot bot2;
    private byte turn;

    public DrawViewIa(Context context) {
        super(context);
        turn=0;


    }


    public DrawViewIa(Context context,Boolean testIA,int difficulty, int agressivity) {
        super(context);
        turn=0;
        bot1=new Bot(0,difficulty,agressivity,this.board);
        ia=testIA;
        if(ia) {
            bot2=new Bot(1,0,1,board);
            turn=1;
        }
    }

    public DrawViewIa(Context context,Boolean testIA,int difficulty, int agressivity,int defi) {
        this(context,testIA,difficulty,agressivity);
        board.initiateChallenge(defi);
        refresh();
    }

    
    // the following method handles all touch events coming from the user
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // ok, an event has occurred. Let's locate it first
        x = event.getRawX();
        y = event.getRawY();
        if(!ia) {
               super.onTouchEvent(event);
        }
        else {
            if (board != null) {
                if (bot1 != null) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if(turn==1) {
                            bot1.play(board);
                            refresh();

                            turn=2;
                            this.invalidate();
                            return true;
                        }
                        else{
                            bot2.play(board);
                            refresh();

                            turn=1;
                            this.invalidate();
                            return true;
                        }
                    }
                }
            }
        }
        this.invalidate();
        return true;


    }


    protected void playIa(){
        LinkedList <BallMove> movementBalls = bot1.play(board);
        BallMove ball = movementBalls.getFirst();
        int absc = ball.toJ - ball.fromJ, ord = ball.toI - ball.fromI;
        if(absc == 1 && ord == 0){
            move = 0;
        }
        else if(absc == 1 && ord == -1){
            move = 1;
        }
        else if(absc == 0 && ord == -1){
            move = 2;
        }
        else if(absc == -1 && ord == 0){
            move = 3;
        }
        else if(absc == -1 && ord == 1){
            move = 4;
        }
        else if(absc == 0 && ord == 1){
            move = 5;
        }
        for(DrawBall e: balls){
            for(BallMove m: movementBalls){
                if(m.fromJ == e.getX() && m.fromI == e.getY()){
                    selectList.add(e);
                }
            }
        }
        movement_rel_offset = 1;
        state = EXECUTE_MOVEMENT;
        executeMovement();
    }

    @Override
    protected void executeMovement(){
        if(movement_rel_offset == 0) {
            int nb = board.doUserMove(move).size();
            if(nb > 1) {
                int vectorX = selectList.get(1).getX() - startBall.getX(), vectorY = selectList.get(1).getY() - startBall.getY();
                for (DrawBall e : balls) {
                    if ((e.getX() == startBall.getX() + nb * vectorX
                            && e.getY() == startBall.getY() + nb * vectorY)
                            || (e.getX() == startBall.getX() + (nb - 1) * vectorX
                            && e.getY() == startBall.getY() + (nb - 1) * vectorY)) {
                        selectList.add(e);
                    }
                }
            }
        }
        if(movement_rel_offset < 100) {
            movement_rel_offset+=10;
            this.invalidate();
            Handler handler = new Handler();
            handler.postDelayed(movementLauncher, 40);
        }
        else{
            state = INITIAL_STATE;
            if(movement_rel_offset == 100) {
                Handler handler = new Handler();
                handler.postDelayed(mMyRunnable, 1000);
            }
            refresh();
            this.invalidate();
        }
    }


    protected Runnable mMyRunnable = new Runnable() {
        @Override
        public void run() {
            playIa();
        }
    };
}