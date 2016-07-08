package com.android.locusideas.core.utils;

/**
 * Created on 28/05/16.
 */

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        //checkNotNull(fragmentManager);
        //checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static class AppBarOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener{
        int currentState;

        final int EXPANDED = 1;
        final int COLLAPSED = 0;
        final int IDLE = -1;
        final OnStateChangedListener onStateChangedListener;

        public AppBarOffsetChangedListener(OnStateChangedListener onStateChangedListener){
            currentState = IDLE;
            this.onStateChangedListener = onStateChangedListener;
        }

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (verticalOffset == 0) {
                if (currentState != EXPANDED) {
                    onStateChangedListener.onStateChanged(EXPANDED);
                }
                currentState = EXPANDED;
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (currentState != COLLAPSED) {
                    onStateChangedListener.onStateChanged(COLLAPSED);
                }
                currentState = COLLAPSED;
            } else {
                if (currentState != IDLE) {
                    onStateChangedListener.onStateChanged(IDLE);
                }
                currentState = IDLE;
            }
        }

        public interface OnStateChangedListener{
            void onStateChanged(int state);
        }
    }
}
