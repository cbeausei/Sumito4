package axelindustry.sumito4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by axel on 19/04/15.
 */
public class IaActivity extends Activity {
    String niveaudifficulté;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        niveaudifficulté=intent.getExtras().getString("Niveaudifficulté");

        if(niveaudifficulté!=null) {
            if (niveaudifficulté.contentEquals("facile")) {
               // Log.d("afficher", "facile");
                DrawViewIa drawViewIa = new DrawViewIa(this,false,0,1);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
            if (niveaudifficulté.contentEquals("moyen")) {
                //Log.d("afficher", "moyen");
                /*OldSelectiondontdelete oldSelectiondontdelete = new OldSelectiondontdelete(this,false,0,1);
                oldSelectiondontdelete.setBackgroundColor(Color.WHITE);
                setContentView(oldSelectiondontdelete);
                */
                DrawViewIa drawViewIa = new DrawViewIa(this,false,0,1);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
            if (niveaudifficulté.contentEquals("difficile")){
                //Log.d("afficher", "difficile");
                DrawViewIa drawViewIa = new DrawViewIa(this,false,0,1);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        }
        else {
            OldSelectiondontdelete oldSelectiondontdelete = new OldSelectiondontdelete(this, true, 0, 1);
            oldSelectiondontdelete.setBackgroundColor(Color.WHITE);
            setContentView(oldSelectiondontdelete);
        }
    }


}