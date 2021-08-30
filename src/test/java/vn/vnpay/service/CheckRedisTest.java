package vn.vnpay.common;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import vn.vnpay.constant.SystemConstant;
import vn.vnpay.utils.JedisPoolUtils;

public class CheckRedisTest {

    @Test
    public void CheckExist(){
        JedisPoolUtils jedisPoolUtils = JedisPoolUtils.getInstance();
        boolean existMerchant = jedisPoolUtils.existKey(SystemConstant.MERCHANTCODE);

        boolean existTerminal = jedisPoolUtils.existKey(SystemConstant.TERMINAL);

        Assertions.assertTrue(existMerchant);
        Assertions.assertTrue(existTerminal);
    }
}
