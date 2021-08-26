package vn.vnpay.common;

import org.apache.logging.log4j.util.Strings;
import vn.vnpay.constant.SystemConstant;
import vn.vnpay.dto.ReqFromMerchantDTO;
import vn.vnpay.dto.RespOfMerchantDTO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Common {

    public static String getLength(String value){
        int max = value.length();
        return String.format("%02d", max);
    }

    public static String makeDataQRCode(ReqFromMerchantDTO request){
        StringBuilder stringBuilder = new StringBuilder();
        String version = SystemConstant.VERSION;
        String method;
        if (Strings.isBlank(request.getAmount()) && Strings.isBlank(request.getTxnId())){
            method = SystemConstant.STATIC;
        } else {
            method = SystemConstant.DYNAMIC;
        }
        String guID = SystemConstant.GUID + getLength(request.getMasterMerCode()) + request.getMasterMerCode();
        String merchantID = SystemConstant.MERCHANT_METHOD + getLength(request.getMerchantCode()) + request.getMerchantCode();
        String storeLabel = SystemConstant.STORE_LABEL + getLength(request.getStoreLabel()) + request.getStoreLabel();
        String expDate = SystemConstant.EXP_DATE + getLength(request.getExpDate()) + request.getExpDate();
        String terminalLabel = SystemConstant.TERMINAL_LABEL + getLength(request.getTerminalId()) + request.getTerminalId();
        String purpose = SystemConstant.PURPOSE + getLength(request.getPurpose()) + request.getPurpose();
        String addInfo = SystemConstant.ADD_INFO + getLength(request.getAddInfo()) + request.getAddInfo();
        String concatMerchant = stringBuilder.append(version)
                .append(SystemConstant.MERCHANT_METHOD + getLength(method) + method)
                .append(SystemConstant.MERCHANT_INFO + getLength(guID+merchantID) + guID+merchantID)
                .append(SystemConstant.MERCHANT_CODE + getLength(request.getMerchantCode()) + request.getMerchantCode())
                .append(SystemConstant.CURRENCY + getLength(request.getCcy()) + request.getCcy())
                .append(SystemConstant.AMOUNT + getLength(request.getAmount()) + request.getAmount())
                .append(SystemConstant.COUNTRY_CODE + getLength(request.getCountryCode()) + request.getCountryCode())
                .append(SystemConstant.MERCHANT_NAME + getLength(request.getMerchantName()) + request.getMerchantName())
                .append(SystemConstant.MERCHANT_CITY + getLength(request.getMerchantCity()) + request.getMerchantCity())
                .append(SystemConstant.POSTAL_CODE + getLength(request.getPostalCode()) + request.getPostalCode())
                .append(SystemConstant.ADD_INFO_FIELD + getLength(storeLabel+expDate+terminalLabel+purpose+addInfo)
                                                                    + storeLabel+expDate+terminalLabel+purpose+addInfo)
                .append(SystemConstant.CRC + getLength(request.getCrc()) + request.getCrc()).toString();
        return concatMerchant;
    }

    public static String hashMD5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger bigInteger = new BigInteger(1, messageDigest);
            String hasSet = bigInteger.toString(16);
            while (hasSet.length() < 32){
                hasSet = "0" + hasSet;
            }
            return hasSet.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(new RuntimeException(e));
        }
    }

    public static String dataCheckSum(ReqFromMerchantDTO reqFromMer) {
        return reqFromMer.getMerchantName() +
                "|" +
                reqFromMer.getServiceCode() +
                "|" +
                reqFromMer.getCountryCode() +
                "|" +
                reqFromMer.getMasterMerCode() +
                "|" +
                reqFromMer.getMerchantCode() +
                "|" +
                reqFromMer.getTerminalId() +
                "|" +
                reqFromMer.getPayType() +
                "|" +
                reqFromMer.getTxnId() +
                "|" +
                reqFromMer.getAmount() +
                "|" +
                reqFromMer.getTipAndFee() +
                "|" +
                reqFromMer.getCcy() +
                "|" +
                reqFromMer.getExpDate();
    }
}
