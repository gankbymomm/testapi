package vn.vnpay.common;

import java.util.UUID;

public class Token {

    public static String getTokenKey(){
        return UUID.randomUUID().toString();
    }
}
