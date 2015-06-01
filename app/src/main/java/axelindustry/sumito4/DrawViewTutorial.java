package axelindustry.sumito4;

/**
 * Created by axel on 21/05/15.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import java.util.LinkedList;

import axelindustry.sumito4.IA.Board;


/**
 * Created by axel on 10/05/15.
 */





public class DrawViewTutorial extends DrawView {
    private Canvas canvas = new Canvas();
    private OnCustomEventListener mListener;
    private TextPaint mTextPaint;


    private String beginofselection , endtutorial,processingselection,endofselection,processingmovementchoice;

    private StaticLayout beginofselectionLayout,endLayout,processingselectionLayout,endofselectionLayout,processingmovementchoiceLayout;
    private Paint p;
    private boolean tutorial=false;
    private int firstDraw=0;


    public DrawViewTutorial(Context context) {
        super(context);
        board.initiateChallenge(9);
    }

    public DrawViewTutorial(Context context, int tutorial){
        this(context);
        board.initiateChallenge(tutorial);
        refresh();
    }
    public DrawViewTutorial(Context context, AttributeSet attrs){
        this(context);
        board.initiateChallenge(9);
        refresh();
    }

    private void layoutcreation(){
        p = new Paint();
        p.setTextSize(h / 30);
        p.setColor(Color.WHITE);
        p.setLinearText(true);
        mTextPaint=new TextPaint(p);
        beginofselection="Pour sélectionner une, deux ou trois boules, passez votre doigt sur celles-ci.";
        endtutorial="Bravo, vous avez réussi le tutoriel de sélection. Vous pouvez continuer à tester si vous le souhaitez.";
        processingselection="Relevez votre doigt lorsque vous êtes satisfaits de la sélection.";
        endofselection="Maintenant, pour choisir votre mouvement, restez appuyé  sur une des boules sélectionnées.";
        processingmovementchoice="Maintenez votre doigt appuyé tout en parcourant l'écran et relachez lorsque vous êtes dans la direction souhaitée.";
        endLayout = new StaticLayout(endtutorial, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        beginofselectionLayout=new StaticLayout(beginofselection, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        endofselectionLayout=new StaticLayout(endofselection, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        processingselectionLayout = new StaticLayout(processingselection, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        processingmovementchoiceLayout=new StaticLayout(processingmovementchoice, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    }



    /* the core of the drawing phase happens here:
    onDraw is called whenever something has changed, have it an actual influence on the drawing or not, eg:
     - when the screen is rotated
     - when invalidate() is called, hence when the user touches the screen
     */

    @Override
    public void onDraw(final Canvas canvas) {
        this.canvas = canvas;
        this.canvas = canvas;

        // h contains the previously-computed height of the screen. There is no need to re-compute all dimensions unless this height has changed
        if(h != canvas.getHeight()){
            this.dimensions();
        }

        if(firstDraw==0){
            layoutcreation();
            firstDraw++;

        }
        // OK, the dimensions are fine, we can draw
        canvas.drawBitmap(fond, 0, 0, null);
        canvas.drawBitmap(plateau, (w - width) / 2, (h - height) / 2, null);

        if (state==INITIAL_STATE&&!tutorial) {

            canvas.save();
            canvas.translate(0, h / 30);
            beginofselectionLayout.draw(canvas);
            canvas.restore();
        }
        else if(tutorial){
            colour=0;
            canvas.save();
            canvas.translate(0, h / 30);
            endLayout.draw(canvas);
            canvas.restore();
        }
        if (state==PROCESSING_SELECTION&&!tutorial) {

            canvas.save();
            canvas.translate(0, h / 30);
            processingselectionLayout.draw(canvas);
            canvas.restore();
        }
        if (state==END_SELECTION&&!tutorial){

            canvas.save();
            canvas.translate(0, h/30);
            endofselectionLayout.draw(canvas);
            canvas.restore();
        }
        if(state==PROCESSING_MOVEMENT_CHOICE&&!tutorial){
            canvas.save();
            canvas.translate(0, h/30);
            processingmovementchoiceLayout.draw(canvas);
            canvas.restore();
        }

        if(state == PROCESSING_MOVEMENT_CHOICE){
            if(list[0] && move == 0)
                canvas.drawBitmap(arrows[0], (w - width) / 2 + 2 * width / 3, (h - height) / 2 + height / 3, null);
            if(list[1] && move == 1)
                canvas.drawBitmap(arrows[1], (w - width) / 2 + width / 2, (h - height) / 2, null);
            if(list[2] && move == 2)
                canvas.drawBitmap(arrows[2], (w - width) / 2 + width / 6, (h - height) / 2, null);
            if(list[3] && move == 3)
                canvas.drawBitmap(arrows[3], (w - width) / 2, (h - height) / 2 + height / 3, null);
            if(list[4] && move == 4)
                canvas.drawBitmap(arrows[4], (w - width) / 2 + width / 6, (h - height) / 2 + 2 * height / 3, null);
            if(list[5] && move == 5)
                canvas.drawBitmap(arrows[5], (w - width) / 2 + width / 2, (h - height) / 2 + 2 * height / 3, null);
        }

        if(state==EXECUTE_MOVEMENT){
            tutorial=true;
        }


        for(DrawBall e : balls) {
            // We have the coordinates of the balls in the game grid. We must compute the coordinates of their representation on the screen
            int coordinates[] = convertCoordinates(e.getX(), e.getY());
            if(selectList.contains(e) && state == EXECUTE_MOVEMENT){
                coordinates[0] += movement_rel_offset * rel_span_x * height * Math.cos(3.1416*move/3) / 100;
                coordinates[1] -= movement_rel_offset * rel_span_x * height * Math.sin(3.1416*move/3) / 100;
            }

            // Now we can draw them
            canvas.drawBitmap(e.getBitmap(), coordinates[0], coordinates[1], null);
        }


    }

    protected void dimensions(){
        h = canvas.getHeight();
        w = canvas.getWidth();

        // we compute the optimal dimensions of the board image: it must be as large as possible, but not outspan the screen
        if (1290 * w < 1487 * h) {
            width = w;
            height = 1290 * w / 1487;
        } else {
            height = h;
            width = 1487 * h / 1290;
        }
        // once done, we draw accordingly
        plateau = Bitmap.createScaledBitmap(plateau, width, height, true);
        for(int i = 0 ; i < 6 ; i++)
            arrows[i] = Bitmap.createScaledBitmap(arrows[i], width/3, height/3, true);

        // the background is easier to draw: there must just be no hole
        fond = Bitmap.createScaledBitmap(fond, Math.max(w, h), Math.max(w, h), true);
        tick = Bitmap.createScaledBitmap(tick, (int)(rel_b_h * height), (int)(rel_b_h * height), true);
        cross = Bitmap.createScaledBitmap(cross, (int)(rel_b_h * height), (int)(rel_b_h * height), true);
        bouleBlanche = Bitmap.createScaledBitmap(bouleBlanche, (int)(rel_b_h * height), (int)(rel_b_h * height), true);
        bouleNoire = Bitmap.createScaledBitmap(bouleNoire, (int)(rel_b_h * height), (int)(rel_b_h * height), true);

        // we draw the balls proportionally to the height of the board
        for(DrawBall e : balls) {
            e.changeBitmap(Bitmap.createScaledBitmap(e.getBitmap(), (int)(rel_b_h * height), (int)(rel_b_h * height), true));
        }
        bouleBleue = Bitmap.createScaledBitmap(bouleBleue, (int)(rel_b_h * height), (int)(rel_b_h * height), true);
        // the score balls same size as the others
        scoreballplayer.changeBitmap(Bitmap.createScaledBitmap(scoreballplayer.getBitmap(), (int) (rel_b_h * height), (int) (rel_b_h * height), true));
        scoreballai.changeBitmap(Bitmap.createScaledBitmap(scoreballai.getBitmap(), (int) (rel_b_h * height), (int) (rel_b_h * height), true));
    }





    public interface OnCustomEventListener {
        public void onEvent();
    }

    public void setCustomEventListener(OnCustomEventListener eventListener) {
        mListener = eventListener;
    }

}