package vn.vnpay.common;

import vn.vnpay.utils.JedisPoolUtils;

public class CheckRedis {

    public static boolean checkExist(String key){
        JedisPoolUtils jedisPoolUtils = JedisPoolUtils.getInstance();
        boolean exist = false;
            if (jedisPoolUtils.existKey(key)){
                exist = true;
            }
    return exist;
    }
}
