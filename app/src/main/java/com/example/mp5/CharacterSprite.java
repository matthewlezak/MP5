package com.example.mp5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CharacterSprite {


    private Bitmap image;
    public static int x,y;


    private float previousDisplacementFromZero;

    public void setX(int input) {
        x = input;
    }

    public static int getX() {return x;}

    public void setY(int input) {y = input;}

    private boolean animationBegin = false;

    private int alongSideTrack = 500;

    private static int sectionToCover = 900;

    private static int sectionAmount = 16;

    private static double iterationAmount = 2.5;

    private static double beginVelocity = 400;

    private static rectObstacle hitBox = new rectObstacle(625,750,775,800, 0);


    public CharacterSprite(Bitmap bmp) {
        x = 500;
        y = 500;
        image = bmp;
    }

    public static int getSectionAmount() {
        return sectionAmount;
    }

    public static int getSectionToCover() {
        return sectionToCover;
    }

    public static double getIterationAmount() {
        return iterationAmount;
    }

    public static double getBeginVelocity() {
        return beginVelocity;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, (int) x, (int) y, null);
    }


    //jump method to be called after player touches screen
    public void jump() {
        x += 1.7 * GameView.getJumpStrength();
        hitBox.setLeft((float) (hitBox.getLeft() + 1.7 * GameView.getJumpStrength()));
        hitBox.setRight((float) (hitBox.getRight() + 1.7 * GameView.getJumpStrength()));
        if (x <= 500) {
            animationBegin = false;
            hitBox.setLeft(625);
            hitBox.setRight(775);
            GameView.setJumpStrength(0);
            x = 500;
        }
    }

    public boolean getAnimationBegin() {
        return this.animationBegin;
    }

    public void setAnimationBegin(boolean value) {
        this.animationBegin = value;
    }

    public static rectObstacle getHitBox() {
        return hitBox;
    }
}
