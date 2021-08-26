package vn.vnpay.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Response;
import vn.vnpay.common.Common;
import vn.vnpay.common.Token;
import vn.vnpay.constant.SystemConstant;
import vn.vnpay.dto.ReqFromMerchantDTO;
import vn.vnpay.dto.RespOfMerchantDTO;
import vn.vnpay.service.MerchantService;
import vn.vnpay.utils.ResponseUtils;
import vn.vnpay.validate.MerchantValidator;

public class MerchantServiceImpl implements MerchantService {

    private static final Logger log = LogManager.getLogger(MerchantServiceImpl.class);

    @Override
    public RespOfMerchantDTO getData(ReqFromMerchantDTO reqFromMerchantDTO) {
        ThreadContext.put(SystemConstant.TOKEN, Token.getTokenKey());

        log.info("Request from merchant : {} ", reqFromMerchantDTO.toString());

        String validateMerchant = MerchantValidator.validate(reqFromMerchantDTO);
        log.info("Validate merchant : {}", validateMerchant);

        if (validateMerchant == null) {
            String data = Common.makeDataQRCode(reqFromMerchantDTO);
            log.info("Response from client : {} ", ResponseUtils.responseFromPartnerDTO(HttpStatus.OK_200, data));
            ThreadContext.clearAll();
            return ResponseUtils.respOfMerchantDTO(SystemConstant.SUCCESS_CODE, SystemConstant.SUCCESS, data);
        }
        log.info("Response from client : {} ", ResponseUtils.responseFromPartnerDTO(HttpStatus.BAD_REQUEST_400, SystemConstant.DATA_NULL));
        ThreadContext.clearAll();
        return ResponseUtils.respOfMerchantDTO(SystemConstant.FAILED_CODE, validateMerchant);
    }

}
