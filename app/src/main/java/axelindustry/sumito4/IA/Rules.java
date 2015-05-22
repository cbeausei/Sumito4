package axelindustry.sumito4.IA;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import axelindustry.sumito4.R;

/**
 * Created by Administrator on 2015/4/16.
 */

public class Rules extends Activity {

    private TextView texttutoriel;
    private Button returns = null;

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.page_rules);

        texttutoriel = (TextView)this.findViewById(R.id.texttutoriel);
        returns = (Button)this.findViewById(R.id.returns);
        returns.setOnClickListener(listenerOfReturns);
        String word = getString(R.string.regles);
        texttutoriel.setTextColor(Color.BLUE);
        texttutoriel.setTextSize(20);
        texttutoriel.setText(word);
        texttutoriel.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    private View.OnClickListener listenerOfReturns = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}

