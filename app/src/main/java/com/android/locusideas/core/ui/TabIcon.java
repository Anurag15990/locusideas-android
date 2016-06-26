package com.android.locusideas.core.ui;

/**
 * Created on 25/06/16.
 */

public class TabIcon{
    int titleStringRes;
    int iconRes;
    int colorRes;

    public TabIcon(int titleStringRes, int iconRes, int colorRes){
        this.titleStringRes = titleStringRes;
        this.iconRes = iconRes;
        this.colorRes = colorRes;
    }

    public int getTitleStringRes() {
        return titleStringRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public int getColorRes() {
        return colorRes;
    }
}
