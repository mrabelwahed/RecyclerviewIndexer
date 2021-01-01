package com.ramadan.indexer;

import android.content.Context;
import android.widget.Toast;

public  class  ToastUtil {
    private static Toast toast;

    /**
     * Display Toast for a short time
     *
     * @param context
     * @param message
     */
    public static Toast showShort(Context context, CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();

        return toast;
    }
}


