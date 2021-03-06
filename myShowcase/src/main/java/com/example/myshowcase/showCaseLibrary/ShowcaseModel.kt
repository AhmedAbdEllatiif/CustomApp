package com.example.myshowcase.showCaseLibrary

import android.graphics.Rect
import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

/**
 * Created by erkutaras on 25.02.2018.
 */
@Parcelize
class ShowcaseModel(
        @DrawableRes val descriptionImageRes: Int,
        val descriptionTitle: String? = null,
        val descriptionText: String? = null,
        val buttonText: String? = null,
        val colorDescTitle: Int = 0,
        val colorDescText: Int = 0,
        val colorButtonText: Int = 0,
        val colorButtonBackground: Int = 0,
        val colorBackground: Int = 0,
        val alphaBackground: Int = 0,
        val colorFocusArea: Int = 0,
        val cxFocusArea: Float = 0.toFloat(),
        val cyFocusArea: Float = 0.toFloat(),
        val radiusFocusArea: Float = 0.toFloat(),
        val rect: Rect? = null,
        val type: ShowcaseType = ShowcaseType.CIRCLE,
        val gradientFocusEnabled: Boolean = false,
        val viewTop: Int = 0,
        val viewButton: Int = 0
) : Parcelable