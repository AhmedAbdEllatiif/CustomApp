package com.ahmed.customapp.Adapters.Decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;


import com.ahmed.customapp.R;

import java.util.Objects;



public class CirclePagerIndicatorDecoration extends RecyclerView.ItemDecoration {


    private final int colorActive = Color.parseColor("#edc873");
    private final int colorInactive = Color.parseColor("#F8F8F8");


    private static final float DP = Resources.getSystem().getDisplayMetrics().density;

    /**
     * Height of the space the indicator takes up at the bottom of the view.
     */
    private final int mIndicatorHeight = (int) (DP * 16);

    /**
     * Indicator stroke width.
     */
    private final static float mIndicatorStrokeWidth = DP * 4;

    /**
     * Indicator width.
     */
    private final float mIndicatorItemLength = DP * 4;
    /**
     * Padding between indicators.
     */
    private final float mIndicatorItemPadding = DP * 8;

    /**
     * Some more natural animation interpolation
     */
    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

    private final Paint mPaint = new Paint();

    private final RecyclerView  recyclerView;
    private Context context;
    public CirclePagerIndicatorDecoration(Context context,RecyclerView recyclerView) {

        mPaint.setStrokeWidth(mIndicatorStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        this.context = context;
        this.recyclerView = recyclerView;

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int itemCount = Objects.requireNonNull(parent.getAdapter()).getItemCount();

        // center horizontally, calculate width and subtract half from center
        float totalLength = mIndicatorItemLength * itemCount;
        float paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding;
        float indicatorTotalWidth = totalLength + paddingBetweenItems;
        float indicatorStartX;
        float indicatorPosY;
        indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 2F;
       // indicatorPosY = parent.getHeight() - mIndicatorHeight -60F;
        indicatorPosY = (parent.getHeight() - mIndicatorHeight) ;

     /*   if (getLocale(activity.getResources()).getDisplayLanguage().equals(Languages.ENGLISH)){
            indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 8F;
            indicatorPosY = parent.getHeight() - mIndicatorHeight -60F;
        }else {
            indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 1.1F;
            indicatorPosY = parent.getHeight() - mIndicatorHeight -60F;
        }*/


        drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount);

        // find active page (which should be highlighted)
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();

        int activePosition = Objects.requireNonNull(layoutManager).findFirstVisibleItemPosition();
        if (activePosition == RecyclerView.NO_POSITION) {
            return;
        }

        // find offset of active page (if the user is scrolling)
        final View activeChild = layoutManager.findViewByPosition(activePosition);
        assert activeChild != null;
       /* int left = activeChild.getLeft();
        int width = activeChild.getWidth();*/

        int left = Objects.requireNonNull(activeChild).getLeft();
        int width = activeChild.getWidth();
        int right = activeChild.getRight();

        //int right = activeChild.getRight();
        float progress = mInterpolator.getInterpolation(left * -1 / (float) width);
        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount);
        /*if (getLocale(view.getContext().getResources()).getDisplayLanguage().equals(Languages.ENGLISH)){
            float progress = mInterpolator.getInterpolation(left * -1 / (float) width);
            drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount);
        }else  {
            //if arabic
            float progress = mInterpolator.getInterpolation(right* -1/(float) width);
            int newActivePos = (itemCount-activePosition)-2;
            //Log.e("LineDecor","ActivePos: " + activePosition);
            //Log.e("LineDecor","newActivePos: " + newActivePos);
            drawHighlights(c, indicatorStartX, indicatorPosY, newActivePos ,progress, itemCount);
        }*/


        // on swipe the active item will be positioned from [-width, 0]
        // interpolate offset for smooth animation
       /* float progress = mInterpolator.getInterpolation(left * -1 / (float) width);

        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress,itemCount);*/
    }

    private void drawInactiveIndicators(Canvas c, float indicatorStartX, float indicatorPosY, int itemCount) {
        mPaint.setColor(colorInactive);

        // width of item indicator including padding
        final float itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

        float start = indicatorStartX;
        for (int i = 0; i < itemCount; i++) {

            c.drawCircle(start, indicatorPosY, mIndicatorItemLength / 2F, mPaint);

            start += itemWidth;
        }
    }

    private void drawHighlights(Canvas c, float indicatorStartX, float indicatorPosY,
                                int highlightPosition, float progress, int itemCount) {
        mPaint.setColor(colorActive);

        // width of item indicator including padding
        final float itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

        if (progress == 0F) {
            // no swipe, draw a normal indicator
            float highlightStart = indicatorStartX + itemWidth * highlightPosition;

            c.drawCircle(highlightStart, indicatorPosY, mIndicatorItemLength / 2F, mPaint);

        } else {
            float highlightStart = indicatorStartX + itemWidth * highlightPosition;
            // calculate partial highlight
            float partialLength = mIndicatorItemLength * progress + mIndicatorItemPadding*progress;

            c.drawCircle(highlightStart + partialLength, indicatorPosY, mIndicatorItemLength / 2F, mPaint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mIndicatorHeight;
    }

}
