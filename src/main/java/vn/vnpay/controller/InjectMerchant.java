package vn.vnpay.controller;

import com.google.inject.Inject;
import vn.vnpay.dto.ReqFromMerchantDTO;
import vn.vnpay.dto.RespOfMerchantDTO;
import vn.vnpay.service.MerchantService;

public class InjectMerchant {

    private MerchantService merchantService;

    @Inject
    protected InjectMerchant(MerchantService merchantService){
        this.merchantService = merchantService;
    }

    protected RespOfMerchantDTO response(ReqFromMerchantDTO reqFromMerchantDTO) {
        return merchantService.getData(reqFromMerchantDTO);
    }

}
