package com.vinhle.searchablespinner.common;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

/**
 * Created by VinhLe on 6/3/2017.
 */

public class StringHelper {

    public static String getString(String data) {
        if (data == null || data.isEmpty()) return "";
        String text = Normalizer.normalize(data, Normalizer.Form.NFD);
//            System.out.println("DATA: " + new String(data.getBytes(), "US-ASCII"));
        text = /*new String(data.getBytes(), "US-ASCII")*/text.replaceAll("[^\\x00-\\x7F]", "");

        return text.toLowerCase();
    }

}
