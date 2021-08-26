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
        ReqFromMerchantDTO reqFromMerchantDTO = new ReqFromMerchantDTO();
        reqFromMerchantDTO.setAppId("VNPAY");
        reqFromMerchantDTO.setServiceCode("03");
        reqFromMerchantDTO.setMasterMerCode("A000000775");
        reqFromMerchantDTO.setMerchantCode("036093001475");
        reqFromMerchantDTO.setCcy("704");
        reqFromMerchantDTO.setAmount("55000.00");
        reqFromMerchantDTO.setCountryCode("CN");
        reqFromMerchantDTO.setMerchantName("BEST TRANSPORT");
        reqFromMerchantDTO.setMerchantCity("BEIJING");
        reqFromMerchantDTO.setPostalCode("10000");
        reqFromMerchantDTO.setBillNumber("987654");
        reqFromMerchantDTO.setStoreLabel("1234");
        reqFromMerchantDTO.setTerminalId("A6008667");
        reqFromMerchantDTO.setPurpose("COSO2");
        reqFromMerchantDTO.setAddInfo("ME");
        reqFromMerchantDTO.setPayType("01");
        reqFromMerchantDTO.setTxnId("123113277");
        reqFromMerchantDTO.setExpDate("2107170755");
        reqFromMerchantDTO.setChecksum("3C927D42F84B5420502AAF29B97402C5");
        reqFromMerchantDTO.setCrc("8TTM");

        MerchantServiceImpl merchantService = new MerchantServiceImpl();
        String checksum = CheckSum.getCheckSum(reqFromMerchantDTO);

        RespOfMerchantDTO request = merchantService.getData(reqFromMerchantDTO);

        Assertions.assertEquals("00", request.getCode());
        Assertions.assertEquals("3C927D42F84B5420502AAF29B97402C5", checksum);
    }
}
