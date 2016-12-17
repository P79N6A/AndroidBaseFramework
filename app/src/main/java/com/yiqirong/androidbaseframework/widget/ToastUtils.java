package com.yiqirong.androidbaseframework.widget;

import android.text.TextUtils;
import android.widget.Toast;


import com.yiqirong.androidbaseframework.MainApplication;


/**
 * 防止重复toast，可自定义toast
 * @author Super LiaoQ
 * @date 2015-4-20
 */
public class ToastUtils {
    private static Toast mToast = null;

    public static void toast(final String msg) {
        showToast(msg);
    }

    public static void debug(final String msg) {
        showToast("调试：" + msg);
    }


    /**
     * 防止重复toast
     * @param msg
     */
    private static void showToast(String msg) {
        if(TextUtils.isEmpty(msg)){
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(MainApplication.getApplication(), msg,
                    Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}