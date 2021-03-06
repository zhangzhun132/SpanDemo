package com.zhangzhun.demo.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.ImageSpan;

/**
 * Created by lishuo on 16/9/30.
 */


public class CenteredImageSpan extends ImageSpan {

    public CenteredImageSpan(Context context, final int drawableRes) {
        super(context, drawableRes);
    }

    public CenteredImageSpan(Drawable drawable) {
        super(drawable);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text,
                     int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        // image to draw
        Drawable b = getDrawable();
        // font metrics of text to be replaced
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        // fm.bottom ,  fm.descent 不同
        int transY = (y + fm.descent + y + fm.ascent) / 2
                - b.getBounds().bottom / 2;

        canvas.save();
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}

