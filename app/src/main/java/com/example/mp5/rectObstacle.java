package com.example.mp5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class rectObstacle {
    private Random leftOrRight = new Random();
    private Paint myPaint = new Paint();
    //This class is mostly going to be used to create Obstacle Objects. Theregffdsafdsajgfgfhfghjgjhhjkhore set the default values to the dimensions of the obstacle rectangles.
    private int bottom = 2750;
    private int top = 2500;
    private float left = 550;
    private float right = 900;
    private int color = -65536;
    private int controlling = 0;

    rectObstacle() {}


    rectObstacle(float leftSet, int topSet, float rightSet, int bottomSet, int setColor) {
        this.left = leftSet;
        this.top = topSet;
        this.right = rightSet;
        this.bottom = bottomSet;
        color = setColor;
    }
    //get methods to be used in collision detection
    public float getRight() {
        return this.right;
    }
    public float getLeft() {
        return this.left;
    }
    public int getTop() {
        return this.top;
    }
    public int getBottom() {
        return this.bottom;
    }

    //set methods to be used in hitBox drawing

    public void setRight(float input) {
        this.right = input;
    }
    public void setLeft(float input) {
        this.left = input;
    }
    public void setTop(int input) {
        this.top = input;
    }
    public void setBottom(int input) {
        this.bottom = input;
    }


    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void reset() {
        this.top = 2500;
        this.bottom = 2750;
        this.left = 550;
        this.right = 900;
    }


    public void Offset (int input) {
        controlling++;
        if (leftOrRight.nextBoolean()) {
            this.top = input + 350;
            this.bottom = this.top + 250;
        } else if (controlling == 2) {
            this.top = randInt(input + 300, input + 1200);
            this.bottom = this.top + 250;
            controlling = 0;
        } else {
            this.top = input + 1150;
            this.bottom = this.top + 250;
        }
        /*
        this.top = randInt(input + 300, input + 1200);
        this.bottom = this.top + 250;
        */
    }

    public void draw(Canvas canvas) {
        myPaint.setColor(color);
        canvas.drawRect(new RectF(left, top, right, bottom),myPaint);
    }
    public void update() {
        if (this.bottom < 0) {
            reset();
        }
        bottom -= 30;
        top -=30;
    }
    public void moveHitbox(float input) {
        this.left += input;
        this.right += input;
    }
    //method to be run on collision.
    public void runCollision() {
    }

    public void setColor(int setColor) {color = setColor;}

}
