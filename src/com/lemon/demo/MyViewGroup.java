package com.lemon.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lemon_bar on 15/1/28.
 */
public class MyViewGroup extends ViewGroup {
    private static final String TAG = MyViewGroup.class.getSimpleName();

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //定义在哪里显示控件
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout, changed: " + changed + "; left: " + l + "; top: " + t + "; right: " + r + "; bottom: " + b);
        int xPos = getPaddingLeft();
        int yPos = getPaddingTop();
        int totalWidth = r - l;
        int widthCursor = xPos;
        int currentLineHeight = 0;
        int totalHeight = yPos;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();

            //old way.
            if (widthCursor + params.leftMargin + width + params.rightMargin > totalWidth) {
                widthCursor = xPos;
                totalHeight += currentLineHeight;
            }
            view.layout(widthCursor + params.leftMargin, totalHeight + params.topMargin, widthCursor + params.leftMargin + width, totalHeight + params.topMargin + height);
            if (height + params.topMargin + params.bottomMargin > currentLineHeight) {
                currentLineHeight = height + params.topMargin + params.bottomMargin;
            }
            widthCursor += params.leftMargin + width + params.rightMargin;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure, widthMeasureSpec: " + widthMeasureSpec + "; heightMeasureSpec: " + heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "MeasureSpec getSize, width: " + width + "; height: " + height);

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChildWithMargins(view, widthMeasureSpec, 0, heightMeasureSpec, 0);
        }

        setMeasuredDimension(width, height);
    }
}
