package com.mocktpo.util;

import org.apache.commons.codec.binary.Base64;

public class CipherUtils {

    private CipherUtils() {
    }

    public static String encode(String plain) {
        try {
            return new String(Base64.encodeBase64(plain.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decode(String code) {
        try {
            return new String(Base64.decodeBase64(code.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
