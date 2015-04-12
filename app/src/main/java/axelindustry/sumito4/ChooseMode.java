package axelindustry.sumito4;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ChooseMode extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_mode);

        Button gamealone=(Button) findViewById(R.id.partiesolo);
        gamealone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sologame=new Intent(ChooseMode.this,DifficultyLevel.class);
                startActivity(sologame);
            }
        });

        Button multiplayerlocalmode=(Button) findViewById(R.id.jouerdeuxjoueurslocal);
        multiplayerlocalmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent multilocal=new Intent(ChooseMode.this,MainActivity.class);
                startActivity(multilocal);
            }
        });

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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
