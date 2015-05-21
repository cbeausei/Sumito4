package axelindustry.sumito4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by axel on 21/05/15.
 */

public class Tutoriel extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context=getApplicationContext();
        DrawViewTutoriel drawViewTutoriel = new DrawViewTutoriel(context,9);
        drawViewTutoriel.setBackgroundColor(Color.WHITE);
        setContentView(R.layout.lom);

    }

}
