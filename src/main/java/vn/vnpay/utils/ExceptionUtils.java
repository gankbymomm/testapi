package vn.vnpay.utils;

import vn.vnpay.exception.CustomException;

public class ExceptionUtils {
    public static CustomException getException(String code, String message) {
        return new CustomException(code, message);
    }
}
