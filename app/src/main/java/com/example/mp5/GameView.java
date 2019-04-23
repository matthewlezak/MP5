package com.example.mp5;

import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.*;
import android.graphics.Canvas;

//tools:context=".GameView">

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private int trackSpriteChange = 0;

    private MainThread thread;

    private CharacterSprite[] characterSpriteList = new CharacterSprite[5];

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);

        getHolder().addCallback(this);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        characterSpriteList[0] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.sprite_1));
        characterSpriteList[1] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.sprite_2));
        characterSpriteList[2] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.sprite_3));
        characterSpriteList[3] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.sprite_4_1));
        characterSpriteList[4] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.sprite_5));
        characterSpriteList[3].y += 5;
        //characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.makingmoney));
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    //on touch event method here

    public void update() {
        if (trackSpriteChange <= 60/4) {
            trackSpriteChange++;
        } else {
            trackSpriteChange = 0;
        }
        for (CharacterSprite sprite : characterSpriteList) {
            sprite.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            if (trackSpriteChange <= 15/4) {
                characterSpriteList[0].draw(canvas);
            } else if (trackSpriteChange <= 30/4) {
                characterSpriteList[1].draw(canvas);
            } else if (trackSpriteChange <= 45/4) {
                characterSpriteList[2].draw(canvas);
            } else {
                characterSpriteList[3].draw(canvas);
            }
        }
    }
}