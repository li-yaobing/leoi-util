package com.github.leoiutil.core.secure;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    private static String SALT = "";

    private static String content;

    public MD5Utils() {
    }

    public MD5Utils(String salt) {
        SALT = salt;
    }

    public static String md5(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    public static String md5WithSalt(String content) {
        return md5(content + SALT);
    }

    public static String md5WithSalt(String content, String salt) {
        return md5(content + salt);
    }
}
