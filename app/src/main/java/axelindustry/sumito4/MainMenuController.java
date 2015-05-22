package axelindustry.sumito4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuController extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        Button newGame=(Button) findViewById(R.id.nouvellepartie);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity=new Intent(MainMenuController.this,ChooseMode.class);
                startActivity(mainActivity);

            }
        });

        Button tutorial=(Button) findViewById(R.id.tutoriel);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (MainMenuController.this, Tutorial.class);
                startActivity(it);
            }
        });

        Button settings=(Button) findViewById(R.id.buttonparam);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (MainMenuController.this, Informations.class);
                startActivity(it);
            }
        });

        Button challenges=(Button) findViewById(R.id.defis);
        challenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (MainMenuController.this, ChallengeActivity.class);
                startActivity(it);
            }
        });

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

*/
}
