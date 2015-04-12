package axelindustry.sumito4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Created by axel on 06/04/15.
 */
public class DifficultyLevel extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_difficulty_layout);

        final RadioGroup difficulty_level=(RadioGroup) findViewById(R.id.niveaudifficulte);

        Button commencer=(Button) findViewById(R.id.commencerpartie);
        commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity=new Intent(DifficultyLevel.this,MainActivity.class);
                MainActivity.putExtra("Niveaudifficulté",difficulty_level.getCheckedRadioButtonId());
                startActivity(MainActivity);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    /*
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


