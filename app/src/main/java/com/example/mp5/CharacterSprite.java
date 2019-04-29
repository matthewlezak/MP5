package com.example.mp5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.Map;
import java.util.HashMap;
public class CharacterSprite {


//gkykyugkuyfyukljhkjlkhjlhlkjkljlkjijkljkkjjljkklhhllkjjlkjljljjhljjhkjhlhkjhfdsaalkjlkjlkjlkjlkkjjlhnkjhkjhkjhkjhkjhkjhkjkjhkhjkhjkhkjhlklhluihhjhlkjh
    private Bitmap image;
    public double x,y;


    private float previousDisplacementFromZero;

    public void setX(int input) {
        x = input;
    }

    public void setY(int input) {
        y = input;
    }

    private boolean animationBegin = false;

    private int alongSideTrack = 500;

    private static int sectionToCover = 900;

    private static int sectionAmount = 16;

    private static double iterationAmount = 2.5;

    private static double beginVelocity = 30;

    private static rectObstacle hitBox = new rectObstacle(625,750,775,800);

    private int velocity =  30;

    private double gravity = -2;
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

    boolean jumping = false;

    //jump method to be called after player touches screen
    public void jump() {
        long timeDifference = (System.nanoTime() / GameView.getSecondConversion()) - GameView.getBeginJumpTime();
        float displacementFromZero = (float) (((velocity * timeDifference) + (0.5 * gravity * (Math.pow(timeDifference, 2)))));
        float amountToDraw = (displacementFromZero - previousDisplacementFromZero);
        x += amountToDraw;
        hitBox.moveHitbox(amountToDraw);
        previousDisplacementFromZero = displacementFromZero;

        /*
        //a mess lies below
        if (alongSideTrack >= 1400) {
            animationBegin = false;
            alongSideTrack = 500;
            x = 500;
            if (hitBox.getTop() != 2) {
                hitBox.setTop(750);
                hitBox.setBottom(800);
                hitBox.setLeft(625);
                hitBox.setRight(775);
            } else {
                hitBox.setBottom(800);
                hitBox.setLeft(625);
                hitBox.setRight(775);
            }

        } else if (alongSideTrack < 612.5) {
            alongSideTrack += 25;
            x += 25;
            hitBox.moveHitbox(25);
        } else if (alongSideTrack < 725) {
            alongSideTrack += 20;
            x += 20;
            hitBox.moveHitbox(20);
        } else if (alongSideTrack < 837.5) {
            alongSideTrack += 15;
            x += 15;
            hitBox.moveHitbox(15);
        } else if (alongSideTrack < 950) {
            alongSideTrack += 10;
            x += 10;
            hitBox.moveHitbox(10);
        } else if (alongSideTrack >= 1287.5) {
            alongSideTrack += 25;
            x -= 25;
            hitBox.moveHitbox(-25);
        } else if (alongSideTrack >= 1175) {
            alongSideTrack += 20;
            x -= 20;
            hitBox.moveHitbox(-20);
        } else  if (alongSideTrack >= 1062.5) {
            alongSideTrack += 15;
            x -= 15;
            hitBox.moveHitbox(-15);
        } else if (alongSideTrack >= 950) {
            alongSideTrack += 10;
            x -= 10;
            hitBox.moveHitbox(-10);
        }
        */


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
