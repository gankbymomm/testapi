package vn.vnpay.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.vnpay.config.ReadConfigYaml;
import vn.vnpay.constant.ValidateConstant;
import vn.vnpay.dto.ReqFromMerchantDTO;

public class CheckSum {

    private static final Logger logger = LogManager.getLogger(CheckSum.class);

    public static String getCheckSum(ReqFromMerchantDTO reqFromMerchantDTO){
        String accessKey = ReadConfigYaml.findAccessKeyByAppId(reqFromMerchantDTO.getAppId());
        if (null == accessKey){
            return ValidateConstant.NOT_EXIST_APP_ID;
        }
        logger.info("Access key of {} : {}", reqFromMerchantDTO.getAppId(), accessKey );

        String dataToHashMD5 = reqFromMerchantDTO.getAppId() + "|" + Common.dataCheckSum(reqFromMerchantDTO) + "|" + accessKey ;
        logger.info("Data to check sum : {}" , dataToHashMD5);

        logger.info("Check sum from request : {} ", reqFromMerchantDTO.getChecksum());

        String checkSum = Common.hashMD5(dataToHashMD5);
        logger.info("Checksum before handler : {} ", checkSum);

        return checkSum;
    }
}
