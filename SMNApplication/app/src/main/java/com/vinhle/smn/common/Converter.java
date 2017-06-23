package com.vinhle.smn.common;

import android.util.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by VinhLe on 5/9/2017.
 */

public class Converter {

    public static <T> List<T> Convert(T[] array) {
        return new ArrayList<T>(Arrays.asList(array));
    }

    public static <T> T[] Convert(List<T> array) {
        // Maybe bugs
        return array.toArray((T[]) java.lang.reflect.Array.newInstance(array.get(0).getClass(), array.size()));
    }

    public static Date Convert(String date) {
        if (date == null || date.isEmpty()) return new Date(Calendar.getInstance().getTime().getTime());

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return new Date(df.parse(date).getTime());
        } catch (ParseException e) {
            Log.e("Converter", e.getMessage(), e);
            return new Date(Calendar.getInstance().getTime().getTime());
        }
    }

    public static Long ConvertToLong(String data) {
        if (data == null || data.isEmpty()) return 0L;

        String str = data.replaceAll("\\D+","");
        return Long.parseLong(str);
    }

    public static Integer ConvertToInt(String data) {
        if (data == null || data.isEmpty()) return 0;

        String str = data.replaceAll("\\D+","");
        return Integer.parseInt(str);
    }
}
