package com.example.mp5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    public static int highScore = 0;

    public static int getHighScore() {
        return highScore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_main);
        SharedPreferences myScore = this.getSharedPreferences("fileScore", Context.MODE_PRIVATE);
        highScore = myScore.getInt("score",0 );
        setContentView(new GameView(this));

    }
}
