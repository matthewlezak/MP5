package com.example.mp5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
public class CharacterSprite {

    private Bitmap image;
    public int x,y;
    private int jumpTrack = 0;

    public void setX(int input) {
        x = input;
    }

    public void setY(int input) {
        y = input;
    }

    public void trackingJump() {
        jumpTrack++;
    }


    public CharacterSprite(Bitmap bmp) {
        x = 500;
        y = 500;
        image = bmp;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }
    public void update() {
    }
    public void jump() {
        for (int i = x; i < 700; i++) {
            if (x < 600) {
                x++;
            } else {
                x--;
            }
        }
    }
}
