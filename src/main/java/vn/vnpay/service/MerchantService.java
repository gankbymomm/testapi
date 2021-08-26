package vn.vnpay.service;

import vn.vnpay.dto.ReqFromMerchantDTO;
import vn.vnpay.dto.RespOfMerchantDTO;

public interface MerchantService {

    RespOfMerchantDTO getData(ReqFromMerchantDTO reqFromMerchantDTO);
}
