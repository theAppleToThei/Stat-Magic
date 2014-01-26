package com.techtalk4geeks.blogspot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

public class HPMeterView extends View {
    private ShapeDrawable mDrawable;

    public HPMeterView(Context context, AttributeSet attrs) {
    super(context, attrs);

    int x = 10;
    int y = 10;
    int width = 300;
    int height = 50;

    mDrawable = new ShapeDrawable(new RectShape());
    mDrawable.getPaint().setColor(0xff0000);
    mDrawable.setBounds(x, y, x + width, y + height);
    }

    protected void onDraw(Canvas canvas) {
    mDrawable.draw(canvas);
    }
    }