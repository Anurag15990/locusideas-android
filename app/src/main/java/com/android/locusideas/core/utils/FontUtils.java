package com.android.locusideas.core.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created on 08/07/16.
 */

public class FontUtils {

    public static Typeface getTypeface(AssetManager assetManager, String typeFace){
        return Typeface.createFromAsset(assetManager,typeFace);
    }

}
