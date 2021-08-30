package vn.vnpay.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import vn.vnpay.config.ReadConfigYaml;
import vn.vnpay.constant.SystemConstant;
import vn.vnpay.dto.ReqFromMerchantDTO;

import java.io.IOException;

public class CheckPrivateKeyTest {

    @Test
    public void getAndCheckKey() throws IOException {
        String privateKey = ReadConfigYaml.findAccessKeyByAppId(SystemConstant.APP_ID);
        String dataInput = "{\"appId\":\"VNPAY\",\"serviceCode\":\"03\",\"masterMerCode\":\"A000000775\"," +
                "\"merchantCode\":\"036093001475\",\"ccy\":\"704\",\"amount\":\"55000.00\"," +
                "\"countryCode\":\"CN\",\"merchantName\":\"BEST TRANSPORT\",\"merchantCity\":\"BEIJING\"," +
                "\"postalCode\":\"10000\",\"billNumber\":\"987654\",\"storeLabel\":\"1234\",\"terminalId\":\"A6008667\"," +
                "\"purpose\":\"COSO2\",\"addInfo\":\"ME\",\"payType\":\"01\",\"txnId\":\"123113277\",\"expDate\":\"2107170755\"," +
                "\"checksum\":\"3C927D42F84B5420502AAF29B97402C5\",\"crc\":\"8TTM\"}";

        ObjectMapper mapperToReqFromMerchant = new ObjectMapper();
        ReqFromMerchantDTO reqFromMerchantDTO = mapperToReqFromMerchant.readValue(dataInput, ReqFromMerchantDTO.class);
        String dataToCheckSum = SystemConstant.APP_ID +"|"+ Common.dataCheckSum(reqFromMerchantDTO) +"|"+ privateKey;

        Assertions.assertEquals("VNPAY|BEST TRANSPORT|03|CN|A000000775|036093001475" +
                "|A6008667|01|123113277|55000.00|null|704|2107170755|908405", dataToCheckSum);
        Assertions.assertEquals("908405", privateKey);
    }
}
