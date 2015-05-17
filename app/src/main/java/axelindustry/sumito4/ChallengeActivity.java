package axelindustry.sumito4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;


/**
 * Created by axel on 17/05/15.
 */
public class ChallengeActivity extends Activity{


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defis_layout);


        Button defi1=(Button) findViewById(R.id.defi1);
        defi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                DrawViewIa drawViewIa = new DrawViewIa(context, false, 0, 1, 1);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        });

        Button defi2=(Button) findViewById(R.id.defi2);
        defi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=getApplicationContext();
                DrawViewIa drawViewIa = new DrawViewIa(context,false,0,1,2);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        });

        Button defi3=(Button) findViewById(R.id.defi3);
        defi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=getApplicationContext();
                DrawViewIa drawViewIa = new DrawViewIa(context,false,0,1,3);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        });

        Button defi4=(Button) findViewById(R.id.defi4);
        defi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=getApplicationContext();
                DrawViewIa drawViewIa = new DrawViewIa(context,false,0,1,4);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        });

        Button defi5=(Button) findViewById(R.id.defi5);
        defi5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=getApplicationContext();
                DrawViewIa drawViewIa = new DrawViewIa(context,false,0,1,5);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        });

        Button defi6=(Button) findViewById(R.id.defi6);
        defi6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=getApplicationContext();
                DrawViewIa drawViewIa = new DrawViewIa(context,false,0,1,6);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        });

        Button defi7=(Button) findViewById(R.id.defi7);
        defi7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=getApplicationContext();
                DrawViewIa drawViewIa = new DrawViewIa(context,false,0,1,7);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        });

        Button defi8=(Button) findViewById(R.id.defi8);
        defi8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=getApplicationContext();
                DrawViewIa drawViewIa = new DrawViewIa(context,false,0,1,8);
                drawViewIa.setBackgroundColor(Color.WHITE);
                setContentView(drawViewIa);
            }
        });



    }
}
