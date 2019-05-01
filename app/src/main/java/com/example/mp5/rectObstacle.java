package com.example.mp5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class rectObstacle {
    private Paint myPaint = new Paint();
    //This class is mostly going to be used to create Obstacle Objects. Therefore set the default values to the dimensions of the obstacle rectangles.
    private int bottom = 2750;
    private int top = 2500;
    private float left = 550;
    private float right = 900;
    private int color = -65536;

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

    public void reset() {
        this.top = 2500;
        this.bottom = 2750;
        this.left = 550;
        this.right = 900;
    }

    public void Offset () {
        this.top = 2850;
        this.bottom = 3100;
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
