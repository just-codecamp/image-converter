package io.scode.imageconvertservice.utils;

import java.util.Base64;

public class DataCodec {

    public static String decodeBase64(String encoded) {
        return new String(Base64.getUrlDecoder().decode(encoded));
    }

    public static String encodeBase64(String s) {
        return Base64.getUrlEncoder().encodeToString(s.getBytes());
    }

}
