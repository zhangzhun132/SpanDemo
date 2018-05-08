package com.zhangzhun.demo.library;

import android.graphics.Color;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;

/**
 * @author zhangzhun
 * @date 2018/5/8
 */

public class BgTouchableSpan extends BackgroundColorSpan {

    private int bgTextColor = Color.TRANSPARENT;

    public BgTouchableSpan(int color) {
        super(color);

    }

    public BgTouchableSpan(Parcel src) {
        super(src);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
    }
}
