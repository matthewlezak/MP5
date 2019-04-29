package com.example.mp5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.*;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private int trackSpriteChange = 0;

    private Paint myPaint = new Paint();

    //moving obstacle initialization. (Eventually more than one will need to be drawn to the screen)

    private rectObstacle rectOb = new rectObstacle();

    private MainThread thread;

    private CharacterSprite[] characterSpriteList = new CharacterSprite[5];

    public Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.background_1);



    //this is a rough draft of the hitBox. (It will eventually need to be updated onTouchEvent)

    //private rectObstacle hitBox = new rectObstacle(625,750,775,800);

    private static long secondConversion = 1_000_000_000L;

    private static long beginJumpTime = 0L;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (characterSpriteList[0].getAnimationBegin() == false) {
            beginJumpTime = System.nanoTime() / secondConversion;
        }
        characterSpriteList[0].setAnimationBegin(true);
        return true;
    }


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
        //create references to objects holding the images of the character sprite animations
        characterSpriteList[0] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_1));
        characterSpriteList[1] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_2));
        characterSpriteList[2] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_3_1));
        characterSpriteList[3] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_4_3));
        characterSpriteList[4] = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.transparent_5_3));
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
        int shouldSwitch = 0;
        for (int i = 1; i < CharacterSprite.getSectionAmount() + 1; i++) {

        }
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


    public void update() {
        System.out.println(beginJumpTime);
        if (characterSpriteList[0].getAnimationBegin()) {
            characterSpriteList[0].jump();
            //characterSpriteList[0].jump();
        }
        //maintain position change for jump animation

        //maintain the variable trackSpriteChange on every update in order to draw the approprite sprite to the screen
        if (trackSpriteChange <= 15) {
            trackSpriteChange++;
        } else {
            trackSpriteChange = 0;
        }

        rectOb.update();
        //collision detection
        if (CharacterSprite.getHitBox().getBottom() > rectOb.getTop() && CharacterSprite.getHitBox().getLeft() < rectOb.getRight() && CharacterSprite.getHitBox().getTop() < rectOb.getBottom()) {
            CharacterSprite.getHitBox().runCollision();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLUE);
        myPaint.setColor(Color.WHITE);
        if (canvas != null) {
            canvas.drawBitmap(b, 0,0 , null);
            CharacterSprite.getHitBox().draw(canvas);
            //draw the ground, the hitbox, and any moving obstacles to the canvas.
            canvas.drawLine(650, -1000, 650, 10000, myPaint);
            //hitBox.draw(canvas);
            rectOb.draw(canvas);
            //draw the sprite animations based the trackSpriteChange variable in the update method.
            if (characterSpriteList[0].getAnimationBegin()) {
                characterSpriteList[0].draw(canvas);
            } else {
                CharacterSprite.getHitBox().draw(canvas);
                if (trackSpriteChange <= 3) {
                    characterSpriteList[0].draw(canvas);
                } else if (trackSpriteChange <= 6) {
                    characterSpriteList[1].draw(canvas);
                } else if (trackSpriteChange <= 9) {
                    characterSpriteList[2].draw(canvas);
                } else if (trackSpriteChange <= 12) {
                    characterSpriteList[3].draw(canvas);
                } else {
                    characterSpriteList[4].draw(canvas);
                }
            }
        }
    }
    public static long getBeginJumpTime() {
        return beginJumpTime;
    }

    public static long getSecondConversion() {
        return secondConversion;
    }
}