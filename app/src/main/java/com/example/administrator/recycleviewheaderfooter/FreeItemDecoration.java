package com.example.administrator.recycleviewheaderfooter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 1 on 2017/5/18.
 */

public class FreeItemDecoration extends RecyclerView.ItemDecoration {
    private int size=2;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View child = parent.getChildAt(i);

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.parseColor("#eeeeee"));
            paint.setStyle(Paint.Style.FILL);

            int startY = child.getTop() - size;
            int startX = child.getLeft();
            int endY = child.getTop() - size;
            int endx = child.getRight();
            c.drawLine(startX,startY,endx,endY,paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,size,0,0);
    }
}
