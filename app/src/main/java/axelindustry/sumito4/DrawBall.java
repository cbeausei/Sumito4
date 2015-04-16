package axelindustry.sumito4;

import android.graphics.Bitmap;

/**
 * Created by Bruno Quercia on 15/04/2015.
 */
public class DrawBall {
    private int x, y, colour;
    private Bitmap bmp;

    public DrawBall(int x, int y, Bitmap bmp, int colour){
        move(x, y);
        this.colour = colour;
        this.bmp = bmp;
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Bitmap getBitmap(){
        return bmp;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void changeBitmap(Bitmap bmp){
        this.bmp = bmp;
    }

    public int getColour(){
        return colour;
    }
}
