package com.sf.util.security;

import com.google.common.base.Charsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class HashUtil {
    private final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final String sha1 = "SHA-1";
    public static final String md5 = "MD5";

    public String hash(String string, String algorithm) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }
        return byteArray2Hex(digest.digest(string.getBytes(Charsets.UTF_8)));

    }

    private String byteArray2Hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (final byte b : bytes) {
            sb.append(hex[(b & 0xF0) >> 4]);
            sb.append(hex[b & 0x0F]);
        }
        return sb.toString();
    }
}
