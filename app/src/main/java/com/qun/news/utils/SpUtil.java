package com.qun.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Qun on 2017/4/21.
 */

public class SpUtil {

    public static final String CONFIG = "config";
    private static SharedPreferences sSp;

    //保存boolean
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sSp == null) {
            sSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sSp.edit().putBoolean(key, value).commit();
    }

    //获取boolean
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sSp == null) {
            sSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sSp.getBoolean(key, defValue);
    }

    //保存String
    public static void saveString(Context context, String key, String value) {
        if (sSp == null) {
            sSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sSp.edit().putString(key, value).commit();
    }

    //获取String
    public static String getString(Context context, String key, String defValue) {
        if (sSp == null) {
            sSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sSp.getString(key, defValue);
    }
}
