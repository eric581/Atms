package com.atms.common.shiro.jar.core;

import java.security.MessageDigest;

public class EncriptionKit {
    public static final String encrypt(String srcStr) {
        try {
            String result = "";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF).toUpperCase();
                result = new StringBuilder().append(result).append(hex.length() == 1 ? "0" : "").append(hex).toString();
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            str = new StringBuilder().append(str).append(Integer.toHexString(ch)).toString();
        }
        return str;
    }

    public static String textEncrypt(String text, String salt) {
        try {
            EncryptionTextKit textEncryptor = new EncryptionTextKit(salt);
            return textEncryptor.encrypt(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String textDecrypt(String text, String salt) {
        try {
            EncryptionTextKit textEncryptor = new EncryptionTextKit(salt);
            return textEncryptor.decrypt(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

