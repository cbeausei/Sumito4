package axelindustry.sumito4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Clement on 20/05/2015.
 */
public class Informations extends Activity{

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_layout);

        TextView infos = (TextView)this.findViewById(R.id.nomjeu);
        infos.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
