package com.example.mp5;

import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.*;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
//fdsafdsa
//fdsaafdsa
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private int trackSpriteChange = 0;

    private Paint myPaint = new Paint();

    private rectObstacle rectOb = new rectObstacle();

    private MainThread thread;

    private CharacterSprite[] characterSpriteList = new CharacterSprite[5];

    private rectObstacle hitbox = new rectObstacle(625,750,775,800);



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
        characterSpriteList[0] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_1));
        characterSpriteList[1] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_2));
        characterSpriteList[2] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_3_1));
        characterSpriteList[3] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_4_3));
        characterSpriteList[4] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_5_3));
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
        if (trackSpriteChange <= 15) {
            trackSpriteChange++;
        } else {
            trackSpriteChange = 0;
        }
        for (CharacterSprite sprite : characterSpriteList) {
            sprite.update();
        }
        rectOb.update();
        if (hitbox.getTop() > rectOb.getTop()) {
            hitbox.runParty();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        myPaint.setColor(Color.WHITE);
        canvas.drawLine(650, -1000, 450, 10000, myPaint);
        if (canvas != null) {
            hitbox.draw(canvas);
            rectOb.draw(canvas);
            if (trackSpriteChange <= 3) {
                characterSpriteList[0].draw(canvas);
            } else if (trackSpriteChange <= 6) {
                characterSpriteList[1].draw(canvas);
            } else if (trackSpriteChange <= 9) {
                characterSpriteList[2].draw(canvas);
            } else if (trackSpriteChange <= 12){
                characterSpriteList[3].draw(canvas);
            } else {
                characterSpriteList[4].draw(canvas);
            }
        }
    }
}