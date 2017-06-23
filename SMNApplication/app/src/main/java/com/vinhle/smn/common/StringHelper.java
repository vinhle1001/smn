package com.vinhle.smn.common;

import android.content.Context;
import android.provider.Settings;

import com.jaredrummler.android.device.DeviceName;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by VinhLe on 5/9/2017.
 */

public class StringHelper {

    public static String Concat(String... params) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : params) {
            if (obj != null) sb.append(obj.toString());
        }
        return sb.toString();
    }

    public static String ConcatWithSplit(char split, String... params) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : params) {
            if (obj != null) sb.append(obj.toString() + split);
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    public static String Convert(Date date) {
        if (date == null) return "";

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

    public static String Convert(java.util.Date date) {
        if (date == null) return "";

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

    public static String Convert(Long money) {
        if (money == null) return "";

        DecimalFormat df = new DecimalFormat("###,###.##");
        return df.format(money);
    }

    public static String ConvertToVN(Long money, String unit) {
        if (money == null) return "";

        DecimalFormat df = new DecimalFormat("###,###.##");
        return df.format(money) + " " + unit;
    }

    public static String GetDeviceId(Context context) {
        if (context == null) return "";
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId == null || deviceId.isEmpty() ?
                DeviceName.getDeviceName() :
                DeviceName.getDeviceName() + "[" + deviceId + "]";
    }
}
