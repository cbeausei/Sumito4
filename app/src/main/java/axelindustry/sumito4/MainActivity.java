package axelindustry.sumito4;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawView drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
    }

    /**
     * Created by Bruno Quercia on 15/04/2015.
     */
    public static class drawBall {
        private Bitmap image;
        private int x, y;

        public void drawBall(Bitmap image, int x, int y){
            this.image = image;
            this.x = x;
            this.y = y;
        }

        public void move(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Bitmap getBitmap()
        {
            return image;
        }
    }
}