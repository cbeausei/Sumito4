package axelindustry.sumito4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import axelindustry.sumito4.IA.Rules;

/**
 * Created by axel on 21/05/15.
 */

public class Tutorial extends Activity  {
   /* private static final byte INITIAL_STATE = 1, PROCESSING_SELECTION = 2, END_SELECTION = 3, PROCESSING_MOVEMENT_CHOICE = 4, EXECUTE_MOVEMENT = 5;
    private TextView textView;
    private DrawViewTutoriel drawViewTutoriel;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_rules_layout);
        Button tutorial=(Button) findViewById(R.id.tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                DrawViewTutorial drawViewTutorial = new DrawViewTutorial(context, 9);
                drawViewTutorial.setBackgroundColor(Color.WHITE);
                setContentView(drawViewTutorial);
            }
        });
        Button regles=(Button) findViewById(R.id.regles);
        regles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (Tutorial.this, Rules.class);
                startActivity(it);
            }
        });


        /*setContentView(R.layout.tutorial_layoutbis);

        textView = (TextView) findViewById(R.id.textttutoriel);
        drawViewTutoriel=(DrawViewTutoriel) findViewById(R.id.viewtutorial);
        Log.d("logo", "bcpdemots");
    if(drawViewTutoriel!=null) {
        Log.d("logo","test444");
        drawViewTutoriel.setCustomEventListener(new DrawViewTutoriel.OnCustomEventListener() {

            @Override
            public void onEvent() {
                byte state = drawViewTutoriel.getState();
                Log.d("logo", "test1");

                if (state == 3) {
                    textView.setText("ttttttttttttttest");
                    Log.d("logo", "test2");
                }
            }

        });

    }
    }

    @Override
    public void onEvent() {
        Log.d("logo", "test3");

    }


    /*private void executeMovement(){
        Log.d("logo", "logo");
        Handler handler = new Handler();
        handler.postDelayed(movementLauncher, 400);
        byte state = drawViewTutoriel.getState();


        textView.setText("ttttttttttttttest");
            Log.d("logo", "logo");

    }
    private Runnable movementLauncher = new Runnable() {
        @Override
        public void run()
        {
            executeMovement();
        }
    };*/
    }
}


