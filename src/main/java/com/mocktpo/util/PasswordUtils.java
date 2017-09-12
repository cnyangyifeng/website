package com.mocktpo.util;

import org.apache.commons.codec.binary.Base64;

public class PasswordUtils {

    private PasswordUtils() {
    }

    public static String encode(String plain) {
        try {
            return new String(Base64.encodeBase64(plain.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
