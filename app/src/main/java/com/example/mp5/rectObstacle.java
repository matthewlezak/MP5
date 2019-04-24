package com.example.mp5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class rectObstacle {
    private Paint myPaint = new Paint();
    //This class is mostly going to be used to create Obstacle Objects. Therefore set the default values to the dimensions of the obstacle rectangles.
    private int bottom = 2250;
    private int top = 2000;
    private int left = 550;
    private int right = 900;
    rectObstacle() {
    }

    rectObstacle(int leftSet, int topSet, int rightSet, int bottomSet) {
        this.left = leftSet;
        this.top = topSet;
        this.right = rightSet;
        this.bottom = bottomSet;
    }
    //get methods to be used in collision detection
    public int getRight() {
        return this.right;
    }
    public int getLeft() {
        return this.left;
    }
    public int getTop() {
        return this.top;
    }
    public int getBottom() {
        return this.bottom;
    }

    public void draw(Canvas canvas) {
        myPaint.setColor(Color.RED);
        canvas.drawRect(new Rect(left, top, right, bottom),myPaint);
    }
    public void update() {
        bottom -= 30;
        top -=30;
    }
    //method to be run on collision.
    public void runCollision() {
        this.top = 2;
    }
}
