package com.example.wdidevashah.expendableslistviewdemo;

import android.content.Context;

public class Utils {
    public static int getPaddingPixels(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
