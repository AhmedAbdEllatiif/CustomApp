package com.ahmed.customapp.MainApp.Helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ahmed.customapp.R;

public class UpperArc extends FrameLayout {

    Paint mPaint, otherPaint, outerPaint, mTextPaint;
    RectF mRectF;
    int mPadding;

    float arcLeft, arcTop, arcRight, arcBottom;

    Path mPath;

    public UpperArc(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(getContext()).inflate(R.layout.upper_layout, this,true);
        this.mPaint = mPaint;
        paint = new Paint();
        path = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
/*


        mTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(pxFromDp(context, 24));

        otherPaint = new Paint();

        outerPaint = new Paint();
        outerPaint.setStyle(Paint.Style.FILL);
        outerPaint.setColor(Color.YELLOW);

        mPadding = 100;


        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);


        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        arcLeft = pxFromDp(context, 20);
        arcTop = pxFromDp(context, 20);
        arcRight = pxFromDp(context, 100);
        arcBottom = pxFromDp(context, 100);


        Point p1 = new Point((int) pxFromDp(context, 80) + (screenWidth / 2), (int) pxFromDp(context, 40));
        Point p2 = new Point((int) pxFromDp(context, 40) + (screenWidth / 2), (int) pxFromDp(context, 80));
        Point p3 = new Point((int) pxFromDp(context, 120) + (screenWidth / 2), (int) pxFromDp(context, 80));

        mPath = new Path();
        mPath.moveTo(p1.x, p1.y);
        mPath.lineTo(p2.x, p2.y);
        mPath.lineTo(p3.x, p3.y);
        mPath.close();

        mRectF = new RectF(screenWidth / 4, screenHeight / 3, screenWidth / 6, screenHeight / 2);
*/


    }



    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }







    Paint paint;
    Path path;
    private static final String TAG = "UpperArc";
    @Override
    protected void dispatchDraw(Canvas canvas) {
        int count = canvas.getSaveCount();

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);

        //arc path
        //canvas.drawPaint(paint);

        float subtractRatio = (float) canvas.getHeight() * .496f;
        float peakOfTheCurve = (float) canvas.getHeight()+ subtractRatio;
        float canvasHeight = (float) canvas.getHeight();
        float canvasWidth = (float) canvas.getWidth();

        Log.e(TAG, "CanvasHeight: " +(float) canvas.getHeight());
        Log.e(TAG, "peakOfTheCurve: " +peakOfTheCurve);
        path.moveTo(0, 0);
        path.lineTo(0,canvasHeight/2);
        path.quadTo(canvasWidth/ 2, peakOfTheCurve,
               canvasWidth, canvasHeight/2);
        path.lineTo((float)canvas.getWidth(),0);

        //canvas.clipPath(path, Region.Op.INTERSECT);
        canvas.clipPath(path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(count);


        //canvas.drawRect(0,0, canvas.getWidth(),canvas.getHeight()/2,paint);
        //canvas.drawArc(50,0,canvas.getWidth()/2,canvas.getHeight()/2,0,150,true,paint);
    }

}
