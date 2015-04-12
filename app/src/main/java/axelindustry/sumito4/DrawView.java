package axelindustry.sumito4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    Canvas canvas = new Canvas();
    Bitmap boulesNoires[], boulesBlanches[], plateau, fond;
    int[] posYn = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2};
    int[] posXn = new int[]{4, 5, 6, 7, 8, 3, 4, 5, 6, 7, 8, 4, 5, 6};
    int[] posYb = new int[]{8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 6, 6, 6};
    int[] posXb = new int[]{0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 2, 3, 4};
    float x = 10, y = 20;
    int w, h, width, height;

    public DrawView(Context context) {
        super(context);
        boulesNoires = new Bitmap[] {BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire),
                               BitmapFactory.decodeResource(getResources(), R.drawable.boulenoire)};
        boulesBlanches = new Bitmap[] {BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche),
                               BitmapFactory.decodeResource(getResources(), R.drawable.bouleblanche)};
        plateau = BitmapFactory.decodeResource(getResources(), R.drawable.cadre);
        fond = BitmapFactory.decodeResource(getResources(), R.drawable.fond);
    }

    private int[] convertCoordinates(int x, int y){
        int absc = (int)(((x+y/2.0-2.0)*45/516.0+29/129.0-1/24.0)*height + (w-width)/2.0);
        y = (int)((y*78/8.0/129.0+26/129.0-1/24.0)*height + (h-height)/2.0);
        x = absc;
        return(new int[]{x, y});
    }

    @Override
    public void onDraw(final Canvas canvas) {
        this.canvas = canvas;
        h = canvas.getHeight();
        w = canvas.getWidth();

        if (1290 * w < 1487 * h) {
            width = w;
            height = 1290 * w / 1487;
        } else {
            height = h;
            width = 1487 * h / 1290;
        }
        plateau = Bitmap.createScaledBitmap(plateau, width, height, true);
        fond = Bitmap.createScaledBitmap(fond, Math.max(w, h), Math.max(w, h), true);
        canvas.drawBitmap(fond, 0, 0, null);
        canvas.drawBitmap(plateau, (w - width) / 2, (h - height) / 2, null);
        for(int i = 0 ; i < 14 ; i++) {
            boulesNoires[i] = Bitmap.createScaledBitmap(boulesNoires[i], height/12, height/12, true);
            boulesBlanches[i] = Bitmap.createScaledBitmap(boulesBlanches[i], height/12, height/12, true);
            int coordinatesn[] = new int[]{};
            int coordinatesb[] = new int[]{};
            coordinatesn = convertCoordinates(posXn[i], posYn[i]);
            coordinatesb = convertCoordinates(posXb[i], posYb[i]);
            canvas.drawBitmap(boulesNoires[i], coordinatesn[0], coordinatesn[1], null);
            canvas.drawBitmap(boulesBlanches[i], coordinatesb[0], coordinatesb[1], null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getRawX();
        y = event.getRawY();
        this.invalidate();
        return true;
    }

}