package com.zhangzhun.demo.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;

import com.zhangzhun.demo.MyAPplication;
import com.zhangzhun.demo.R;


/**
 * @author zhangzhun
 * @date 2018/5/6
 */

public class AutoClickableTextView extends AppCompatTextView {

    private static char MATCH_STRING = '\u200b';
    private AutoClickListener autoClickListener;
    private @ColorInt int linkColor = Color.BLUE;
    private @ColorInt int pressedColor = Color.BLUE;
    private @ColorInt int bgColor = Color.TRANSPARENT;


    public AutoClickableTextView(Context context) {
        this(context, null);
    }

    public AutoClickableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoClickableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null && getContext() != null) {
            TypedArray typedArray =getContext().obtainStyledAttributes(attrs, R.styleable.AutoClickableTextView);
            if (typedArray != null) {
                linkColor = typedArray.getColor(R.styleable.AutoClickableTextView_link_color, Color.BLUE);
                pressedColor = typedArray.getColor(R.styleable.AutoClickableTextView_pressed_color, Color.BLUE);
                bgColor = typedArray.getColor(R.styleable.AutoClickableTextView_bg_color, Color.TRANSPARENT);
                typedArray.recycle();
            }
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (TextUtils.isEmpty(text)) {
            super.setText(text, type);
            return;
        }
        setMovementMethod(new AutoLinkMovementMethod(bgColor));
        super.setText(handleUrl(text), type);
    }


    private CharSequence handleUrl(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return null;
        }
        return getHighlightString(charSequence.toString());
    }

    public  CharSequence getHighlightString(String string) {
        if (TextUtils.isEmpty(string)) {
            return string;
        }
        if (string.indexOf(MATCH_STRING) < 0) {
            return string;
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(string);

        int start = 0;
        int length = string.length();
        while (start < length) {
            int begin = string.indexOf(MATCH_STRING, start);
            if (begin == -1) {
                break;
            }
            start = begin + 1;
            int end = string.indexOf(MATCH_STRING, start);
            if (end == -1) {
                break;
            }
            start = end + 1;
            ForegroundColorSpan span = new ForegroundColorSpan(getCurrentTextColor());
            Drawable drawable = MyAPplication.application.getDrawable(R.mipmap.world_link);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            CenteredImageSpan imageSpan = new CenteredImageSpan(drawable);
            builder.setSpan(span, begin, end + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            final String temp = string.subSequence(begin, end + 1).toString();

            TouchableSpan touchableSpan = new TouchableSpan(linkColor, pressedColor,  false) {
                @Override
                public void onClick(View widget) {
                    if (autoClickListener != null) {
                        autoClickListener.onClick(temp);
                    }
                }
            };

            builder.setSpan(touchableSpan, begin, end + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(imageSpan, begin, begin + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

    public void setAutoClickListener(AutoClickListener autoClickListener) {
        this.autoClickListener = autoClickListener;
    }
}
