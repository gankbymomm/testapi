package vn.vnpay.common;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import vn.vnpay.config.ReadConfigYaml;

public class CheckPrivateKeyTest {

    @Test
    public void getAndCheckKey(){
        String privateKey = ReadConfigYaml.findAccessKeyByAppId("BIDV");


        Assertions.assertEquals("970466", privateKey);
    }
}
