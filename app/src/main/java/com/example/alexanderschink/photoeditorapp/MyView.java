package com.example.alexanderschink.photoeditorapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class MyView extends View {
    private Paint paint;
    private PointF[] points;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.GREEN);
    }

    public void setPoints(PointF[] points) {
        this.points = points;

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

       if (points == null) return;

       for (PointF point : points) {
           RectF rect = new RectF(point.x, point.y, point.x + 10, point.y + 10);
           canvas.drawRect(rect, paint);
       }


    }
}
