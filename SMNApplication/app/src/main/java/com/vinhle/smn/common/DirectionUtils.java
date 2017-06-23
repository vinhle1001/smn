package com.vinhle.smn.common;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by VinhLe on 5/8/2017.
 */

public class DirectionUtils {

    public static void changeActivity(Activity mActivity, int animIn, int animOut, boolean hasFinish, Intent mIntent) {
        if (mActivity == null || mIntent == null) {
            return;
        }

        mActivity.startActivity(mIntent);
        mActivity.overridePendingTransition(animIn, animOut);
        if (hasFinish) {
            mActivity.finish();
        }

    }

}
