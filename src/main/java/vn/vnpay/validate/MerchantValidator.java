package vn.vnpay.validate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import vn.vnpay.common.CheckRedis;
import vn.vnpay.common.CheckSum;
import vn.vnpay.constant.SystemConstant;
import vn.vnpay.constant.ValidateConstant;
import vn.vnpay.dto.ReqFromMerchantDTO;

public class MerchantValidator {

    private static final Logger logger = LogManager.getLogger(MerchantValidator.class);

    public static String validate(ReqFromMerchantDTO reqFromMerchantDTO) {
        // validate input
        if (Strings.isBlank(reqFromMerchantDTO.getAppId()))
            return ValidateConstant.APP_ID;

        if (Strings.isBlank(reqFromMerchantDTO.getMerchantName()))
            return ValidateConstant.MERCHANT_NAME;

        if (Strings.isBlank(reqFromMerchantDTO.getServiceCode()))
            return ValidateConstant.SERVICE_CODE;

        if (Strings.isBlank(reqFromMerchantDTO.getCountryCode()))
            return ValidateConstant.COUNTRY_CODE;

        if (Strings.isBlank(reqFromMerchantDTO.getMasterMerCode()))
            return ValidateConstant.MASTER_CODE;

        if (Strings.isBlank(reqFromMerchantDTO.getMerchantCode()))
            return ValidateConstant.MERCHANT_CODE;

        if (Strings.isBlank(reqFromMerchantDTO.getTerminalId()))
           return ValidateConstant.TERMINAL_ID;

        if (Strings.isBlank(reqFromMerchantDTO.getPayType()))
            return  ValidateConstant.PAY_TYPE;

        // check exist in redis
        boolean existMerchantCode = CheckRedis.checkExist(reqFromMerchantDTO.getMerchantCode());
        logger.info("Exist merchantCode in redis : {} ", existMerchantCode);
        if (existMerchantCode){
            return ValidateConstant.NOT_EXIST_MERCHANTCODE;
        }

        String terminal = reqFromMerchantDTO.getMerchantCode() + reqFromMerchantDTO.getTerminalId();
        boolean existTerminal = CheckRedis.checkExist(terminal);
        logger.info("Exist terminal in redis : {} ", existTerminal);
        if (existTerminal){
            return ValidateConstant.NOT_EXIST_TERMINAL;
        }

        // check sum data
        String dataCheckSum = CheckSum.getCheckSum(reqFromMerchantDTO);
        if (!reqFromMerchantDTO.getChecksum().equals(dataCheckSum)){
            return SystemConstant.NOT_EQUALS_CHECK_SUM;
        }
        return null;
    }
}