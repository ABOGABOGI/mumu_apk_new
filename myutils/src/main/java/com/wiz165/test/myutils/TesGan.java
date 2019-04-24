package com.wiz165.test.myutils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by root on 01/11/17.
 */

public class TesGan {
    /**
     * tesss
     * @param dp
     * @return
     */
    public static float convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
