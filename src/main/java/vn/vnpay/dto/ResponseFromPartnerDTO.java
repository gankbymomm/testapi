package vn.vnpay.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseFromPartnerDTO {

    private int statusCode;
    private Object data;

}
