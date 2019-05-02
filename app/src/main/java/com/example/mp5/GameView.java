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
import android.content.Intent;
import android.content.SharedPreferences;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private int trackSpriteChange = 0;

    private int score = 0;

    private Paint myPaint = new Paint();

    private Paint textPaint = new Paint();

    //moving obstacle initiamhkjlization. (Eventually njnfdsafdsjkjknkhjklhlhlhgkhgkhhgjhghkgjkgjkkjjkmofdsaffdre than one will need to be drawn t;l;lk;;lk;lokpklkijk,juhjhjo the screen)uyhkjhkjh

    private rectObstacle rectOb = new rectObstacle();

    private rectObstacle testing = new rectObstacle();

    private MainThread thread;

    private int counter = 30;

    private CharacterSprite[] characterSpriteList = new CharacterSprite[5];

    private static boolean isDead = false;

    public Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.spacepixelated);

    private Context context = getContext();

    private Intent i = new Intent(context, Main2Activity.class);




    //this is a rough draft of the hitBox. (It will eventually need to be updated onTouchEvent)

    //private rectObstacle hitBox = new rectObstacle(625,750,775,800);

    private static long secondConversion = 1_000_000_000L;

    private static long beginJumpTime = 0L;

    private static float jumpStrength = 0, weight = 1;

    public static boolean getIsDead() {
        return isDead;
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (CharacterSprite.getX() == 500) {
            jumpStrength = 24;
        }
        /*
        if (isDead) {
            counter = 0;
            isDead = false;
        } else if (counter == 30) {
            characterSpriteList[0].setAnimationBegin(true);
        }
        */
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
        testing.Offset(rectOb.getBottom() + 400);
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

        System.out.println(MainActivity.getHighScore());
        System.out.println(testing.getBottom());
        /*
        if (counter < 30) {
            counter++;
        }
        */
        if (!isDead) {
            if (rectOb.getBottom() < 0) {
                score += 1;
            }
        }
        System.out.println(beginJumpTime);
        if (characterSpriteList[0].getAnimationBegin()) {
            characterSpriteList[0].jump();
        }
        //maintain position change for jump animation

        //maintain the variable trackSpriteChange on kljjkevery ukjhjjhfdsajjklkhjkhjkkjhljjkhgkgkjghkpjhgjhgjhdate in ordekjhkjr to draw the appropriate sprite to the screen
        if (trackSpriteChange <= 15) {
            trackSpriteChange++;
        } else {
            trackSpriteChange = 0;
        }
        rectOb.update();
        testing.setBottom(testing.getBottom() - 30);
        testing.setTop(testing.getTop() - 30);
        if (rectOb.getBottom() < 2600 && rectOb.getBottom() > 2500 && testing.getBottom() < 0) {
            testing.Offset(rectOb.getBottom() + 400);
        }
        //collision detection
        if (CharacterSprite.getHitBox().getBottom() > rectOb.getTop() && CharacterSprite.getHitBox().getLeft() < rectOb.getRight() && CharacterSprite.getHitBox().getTop() < rectOb.getBottom()) {
            isDead = true;
            context.startActivity(i);
            isDead = false;
            //CharacterSprite.getHitBox().runCollision();
        }
        if (CharacterSprite.getHitBox().getBottom() > testing.getTop() && CharacterSprite.getHitBox().getLeft() < testing.getRight() && CharacterSprite.getHitBox().getTop() < testing.getBottom()) {
            isDead = true;
            SharedPreferences storedScore = context.getSharedPreferences("fileScore", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = storedScore.edit();
            editor.putInt("score", score);
            editor.commit();
            context.startActivity(i);
            isDead = false;
            //CharacterSprite.getHitBox().runCollision();
        }


        jumpStrength -= weight;

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLUE);
        myPaint.setColor(Color.WHITE);
        if (canvas != null) {
            //canvas.drawBitmap(b, 0,0 , null);
            CharacterSprite.getHitBox().draw(canvas);
            //draw the ground, the hitbox, and any moving obstacles to the canvas.
            canvas.drawLine(650, -1000, 650, 10000, myPaint);
            //hitBox.draw(canvas);
            //testing.setColor(-16776961);
            testing.draw(canvas);
            rectOb.draw(canvas);
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(100);
            canvas.drawText(Integer.toString(score), 1200, 200, textPaint);
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


    public static float getJumpStrength() {return jumpStrength;}

    public static void setJumpStrength(float setJumpStrength) {
        jumpStrength = setJumpStrength;
    }

}