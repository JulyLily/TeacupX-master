package com.lily.teacup.banner.transformer;


import android.view.View;

import androidx.core.view.ViewCompat;

public class FadePageTransformer extends TAPPageTransformer {

    @Override
    public void handleInvisiblePage(View view, float position) {
    }

    @Override
    public void handleLeftPage(View view, float position) {
        ViewCompat.setTranslationX(view, -view.getWidth() * position);
        ViewCompat.setAlpha(view, 1 + position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewCompat.setTranslationX(view, -view.getWidth() * position);
        ViewCompat.setAlpha(view, 1 - position);
    }

}