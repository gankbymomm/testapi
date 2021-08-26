package vn.vnpay.utils;

import vn.vnpay.dto.RespOfMerchantDTO;
import vn.vnpay.dto.ResponseFromPartnerDTO;

public class ResponseUtils {

    public static RespOfMerchantDTO respOfMerchantDTO(String code, String message, String data){
        return RespOfMerchantDTO.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
    public static RespOfMerchantDTO respOfMerchantDTO(String code, String message){
        return RespOfMerchantDTO.builder()
                .code(code)
                .message(message)
                .build();
    }
    public static ResponseFromPartnerDTO responseFromPartnerDTO(int statusCode, Object data){
        return ResponseFromPartnerDTO.builder()
                .statusCode(statusCode)
                .data(data)
                .build();
    }
}
