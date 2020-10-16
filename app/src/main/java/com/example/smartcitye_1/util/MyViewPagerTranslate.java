package com.example.smartcitye_1.util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/14 at 17:43
 */
public class MyViewPagerTranslate implements ViewPager.PageTransformer {
    private ViewPager viewPager;
    private int ToffsetX;

    public MyViewPagerTranslate(Context context) {
        ToffsetX = dp2px(context, 180);
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (viewPager == null) {
            viewPager = (ViewPager) page.getParent();
        }
        int leftScreen = page.getLeft() - viewPager.getScrollX();
        int currentXInViewParent = leftScreen + page.getMeasuredWidth() / 2;
        int offsetX = currentXInViewParent - viewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * 0.38f / viewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);
        if (scaleFactor > 0) {
            page.setScaleY(scaleFactor);
            page.setScaleX(scaleFactor);
            page.setTranslationX(-ToffsetX * offsetRate);
        }
    }

    private int dp2px(Context context, int value) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (value * m + 0.5f);
    }
}
