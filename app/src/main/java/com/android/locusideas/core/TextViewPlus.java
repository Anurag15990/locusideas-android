package com.android.locusideas.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import com.locusideas.locusideas.R;
import java.util.HashMap;

/**
 * Created on 24/05/16.
 */
public class TextViewPlus extends TextView {

    private static final String TAG = "TextViewPlus";
    private static HashMap<String,Typeface> sCachedTypeface = new HashMap<>();
    public TextViewPlus(Context context) {
        super(context);
    }

    public TextViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public TextViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
        String customFont = a.getString(R.styleable.TextViewPlus_customFont);

        if(customFont != null){
            setCustomFont(ctx, customFont);
        }

        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String font) {
        Typeface tf = sCachedTypeface.get(font);

        if (tf == null){
            try {
                tf = Typeface.createFromAsset(ctx.getAssets(), font);
                sCachedTypeface.put(font, tf);
            } catch (Exception e) {
                Log.e(TAG, "Could not get typeface: " + e.getMessage());
                return false;
            }
        }

        setTypeface(tf);

        return true;
    }

}

