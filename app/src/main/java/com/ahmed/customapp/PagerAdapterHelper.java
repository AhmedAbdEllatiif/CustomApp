package com.ahmed.customapp;

import androidx.cardview.widget.CardView;

public interface PagerAdapterHelper {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
