package com.example.mp5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
public class CharacterSprite {

    private Bitmap image;
    public int x,y;


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
}
