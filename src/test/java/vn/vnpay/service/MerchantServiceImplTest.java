package vn.vnpay.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import vn.vnpay.common.CheckSum;
import vn.vnpay.dto.ReqFromMerchantDTO;
import vn.vnpay.dto.RespOfMerchantDTO;
import vn.vnpay.service.impl.MerchantServiceImpl;

public class MerchantServiceImplTest {

    @Test
    public void getDataTest(){
        String dataInput = "{\"appId\":\"VNPAY\",\"serviceCode\":\"03\",\"masterMerCode\":\"A000000775\"," +
                "\"merchantCode\":\"036093001475\",\"ccy\":\"704\",\"amount\":\"55000.00\"," +
                "\"countryCode\":\"CN\",\"merchantName\":\"BEST TRANSPORT\",\"merchantCity\":\"BEIJING\"," +
                "\"postalCode\":\"10000\",\"billNumber\":\"987654\",\"storeLabel\":\"1234\",\"terminalId\":\"A6008667\"," +
                "\"purpose\":\"COSO2\",\"addInfo\":\"ME\",\"payType\":\"01\",\"txnId\":\"123113277\",\"expDate\":\"2107170755\"," +
                "\"checksum\":\"3C927D42F84B5420502AAF29B97402C5\",\"crc\":\"8TTM\"}";

        ObjectMapper mapperToReqFromMerchant = new ObjectMapper();
        ReqFromMerchantDTO reqFromMerchantDTO = mapperToReqFromMerchant.readValue(dataInput, ReqFromMerchantDTO.class);

        MerchantServiceImpl merchantService = new MerchantServiceImpl();
        String checksum = CheckSum.getCheckSum(reqFromMerchantDTO);

        RespOfMerchantDTO request = merchantService.getData(reqFromMerchantDTO);

        Assertions.assertEquals("00", request.getCode());
        Assertions.assertEquals("3C927D42F84B5420502AAF29B97402C5", checksum);
    }
}
