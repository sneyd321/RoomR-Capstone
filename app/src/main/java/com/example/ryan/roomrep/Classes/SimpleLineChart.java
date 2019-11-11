package com.example.ryan.roomrep.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashMap;


public class SimpleLineChart extends View {

    private int mWidth, mHeight;


    private float mYAxisFontSize = 24;


    private int mLineColor = Color.parseColor("#00BCD4");


    private float mStrokeWidth = 8.0f;


    private HashMap<Integer, Integer> mPointMap;


    private float mPointRadius = 10;


    private String mNoDataMsg = "no data";


    private String[] mXAxis = {};


    private String[] mYAxis = {};

    public SimpleLineChart(Context context) {
        this(context, null);
    }

    public SimpleLineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("width must be EXACTLY,you should set like android:width=\"200dp\"");
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }else if(widthMeasureSpec == MeasureSpec.AT_MOST){

            throw new IllegalArgumentException("height must be EXACTLY,you should set like android:height=\"200dp\"");
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(mXAxis.length==0||mYAxis.length==0){
            throw new IllegalArgumentException("X or Y items is null");
        }

        Paint axisPaint = new Paint();
        axisPaint.setTextSize(mYAxisFontSize);
        axisPaint.setColor(Color.parseColor("#3F51B5"));

        if (mPointMap == null || mPointMap.size() == 0) {
            int textLength = (int) axisPaint.measureText(mNoDataMsg);
            canvas.drawText(mNoDataMsg, mWidth/2 - textLength/2, mHeight/2, axisPaint);
        } else {




            int[] yPoints = new int[mYAxis.length];



            int yInterval = (int) ((mHeight - mYAxisFontSize - 2) / (mYAxis.length));


            Paint.FontMetrics fm = axisPaint.getFontMetrics();
            int yItemHeight = (int) Math.ceil(fm.descent - fm.ascent);

            Log.e("wing", mHeight + "");
            for (int i = 0; i < mYAxis.length; i++) {
                canvas.drawText(mYAxis[i], 0, mYAxisFontSize + i * yInterval, axisPaint);
                yPoints[i] = (int) (mYAxisFontSize + i * yInterval);


            }



            int[] xPoints = new int[mXAxis.length];

            Log.e("wing", xPoints.length + "");

            int xItemX = (int) axisPaint.measureText(mYAxis[1]);


            int xOffset = 50;

            int xInterval = (int) ((mWidth - xOffset) / (mXAxis.length));

            int xItemY = (int) (mYAxisFontSize + mYAxis.length * yInterval);

            for (int i = 0; i < mXAxis.length; i++) {
                canvas.drawText(mXAxis[i], i * xInterval + xItemX + xOffset, xItemY, axisPaint);
                xPoints[i] = (int) (i * xInterval + xItemX + axisPaint.measureText(mXAxis[i]) / 2 + xOffset + 10);

            }



            Paint pointPaint = new Paint();

            pointPaint.setColor(mLineColor);

            Paint linePaint = new Paint();

            linePaint.setColor(mLineColor);
            linePaint.setAntiAlias(true);

            linePaint.setStrokeWidth(mStrokeWidth);
            pointPaint.setStyle(Paint.Style.FILL);


            for (int i = 0; i < mXAxis.length; i++) {
                if (mPointMap.get(i) == null) {
                    throw new IllegalArgumentException("PointMap has incomplete data!");
                }


                canvas.drawCircle(xPoints[i], yPoints[mPointMap.get(i)], mPointRadius, pointPaint);
                if (i > 0) {
                    canvas.drawLine(xPoints[i - 1], yPoints[mPointMap.get(i - 1)], xPoints[i], yPoints[mPointMap.get(i)], linePaint);
                }
            }

        }
    }


    public void setData(HashMap<Integer,Integer> data){
        mPointMap = data;
        invalidate();
    }


    public void setYItem(String[] yItem){
        mYAxis = yItem;
    }


    public void setXItem(String[] xItem){
        mXAxis = xItem;
    }

    public void setLineColor(int color){
        mLineColor = color;
        invalidate();
    }
}


