package com.mim.sharepreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtHighScore,txtScore;
    Button btnPlay,btnReset;

    SharedPreferences sp;
    SharedPreferences.Editor editor; //for edit xml file;

    private final String SP_GAME="com.mim.sharepreference.score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    txtHighScore=(TextView)findViewById(R.id.txt_high_score);
    txtScore=(TextView)findViewById((R.id.txt_score));

    btnPlay=(Button)findViewById(R.id.btn_play);
    btnReset=(Button)findViewById(R.id.btn_reset);

    sp=getSharedPreferences(SP_GAME,MODE_PRIVATE);
    editor=sp.edit();

    int highScore=sp.getInt("high_score",0);
    txtHighScore.setText("High Score: " +highScore);

    btnPlay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Random r=new Random();
            int score=r.nextInt(1000);

            txtScore.setText(String.valueOf(score));


            int getSaveScore=sp.getInt("high_score",0);

            if(score>getSaveScore)
            {
                txtHighScore.setText("High Score: "+score);
                editor.putInt("high_score",score);
                editor.commit();//for save
            }


        }
    });


    btnReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editor.putInt("high_score",0);
            editor.commit();

            txtHighScore.setText("High Score: 0");
            txtScore.setText("0");
        }
    });









    }
}
