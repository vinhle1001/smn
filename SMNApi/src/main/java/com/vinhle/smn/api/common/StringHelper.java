package com.vinhle.smn.api.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by VinhLe on 4/16/2017.
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

    public static String encodeSession(String email) throws Exception {
        String dateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Key key = MacProvider.generateKey();
        String compactJws = Jwts.builder()
                .setSubject(Concat(email, formatter.format(Calendar.getInstance().getTime())))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return compactJws;
    }

    // entity maybe 3 character
    public static String generateSKU(String oldCode, String entity) {
        if (oldCode != null && !oldCode.isEmpty()) return oldCode;

        Long template = 123456789012345L;

        String dateFormat = "yyyyMMddHHmmssS";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String date = formatter.format(Calendar.getInstance().getTime());
        Long unique = Long.parseLong(date) + template;

        return entity != null ? entity + unique.toString() : unique.toString();
    }
}
