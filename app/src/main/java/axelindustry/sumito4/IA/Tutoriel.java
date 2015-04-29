package axelindustry.sumito4.IA;

import android.app.Activity;
import android.content.Intent;
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

public class Tutoriel extends Activity {

    private TextView texttutoriel;
    private Button returns = null;

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.page_tutorial);

        texttutoriel = (TextView)this.findViewById(R.id.texttutoriel);
        returns = (Button)this.findViewById(R.id.returns);
        returns.setOnClickListener(listenerOfReturns);
        String word = "                   Version Francaise\n" +
                "Le joueur possédant les billes noires commence.À tour de rôle les joueurs déplacent 1," +
                "2 ou 3 billes d’un mouvement vers des cases voisines. Le déplacement peut se faire en ligne ou latéralement.\n" +
                "Pour pouvoir pousser les billes de son adversaire, le joueur doit se trouver en position de sumito, c’est-à-dire en" +
                " supériorité numérique. Une ligne de 3 billes ou plus ne peut jamais être poussée par l’adversaire. Les sumitos " +
                "possibles sont donc :\n" +
                "\n" +
                "le 3 contre 2,\n" +
                "le 3 contre 1,\n" +
                "le 2 contre 1\n" +
                "Dès que la sixième bille d’un joueur est éjectée du tablier de jeu, son adversaire a gagné.\n" +
                "\n" +
                "                    Version Chinoise\n" +
                "官方规则具有多种初始布置，最常见是十一颗棋珠放入靠近己方的两横行位置，其余三颗棋珠置于第三横行的中间三个棋位。\n" +
                "每方需让己方棋珠朝六方向之一移动一格，有两种移动方式：\n" +
                "侧移：一到三颗相连的己方棋珠，朝连线方向以外的方向一起移至空棋位。\n" +
                "推移：两到三颗相连的己方棋珠，遵循连线方向，推移至空棋位，或移至敌方棋位。\n" +
                "只有推动己棋数大于连线方向的敌棋，并且该些敌棋没被同线己棋夹住，才可纵队移动至敌方棋位，并将该方敌棋皆推动一格。原先在棋盘边的一颗敌棋，被推移后会被移除棋盘。\n" +
                "移除六颗敌棋者获胜";
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

