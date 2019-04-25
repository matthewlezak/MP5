package com.example.mp5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
public class CharacterSprite {



    private Bitmap image;
    public double x,y;


    public void setX(int input) {
        x = input;
    }

    public void setY(int input) {
        y = input;
    }

    private boolean animationBegin = false;

    private int alongSideTrack = 500;

    private static rectObstacle hitBox = new rectObstacle(625,750,775,800);

    private int Velocity = 20;

    public CharacterSprite(Bitmap bmp) {
        x = 500;
        y = 500;
        image = bmp;
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, (int) x, (int) y, null);
    }

    boolean jumping = false;

    //jump method to be called after player touches screen
    public void jump() {
        if (alongSideTrack == 1400) {
            animationBegin = false;
            alongSideTrack = 500;
        } else if (alongSideTrack < 950) {
            alongSideTrack += 10;
            x += 10;
            hitBox.moveHitbox(10);
        } else if (alongSideTrack >= 950) {
            alongSideTrack += 10;
            x -= 10;
            hitBox.moveHitbox(-10);
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
