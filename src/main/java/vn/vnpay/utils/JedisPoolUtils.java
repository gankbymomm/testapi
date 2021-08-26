package vn.vnpay.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.vnpay.constant.SystemConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {

    private static final Logger logger = LogManager.getLogger(JedisPoolUtils.class);
    private static volatile JedisPoolUtils instance = null;
    private static JedisPool jedisPool;
    private static Properties properties = new Properties();

    private static Properties readFileProperties() {
        try (InputStream fileInputStream = new FileInputStream(SystemConstant.PATH_FILE_REDIS)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Error when read file", e);
        }
        return properties;
    }

    public static JedisPoolUtils getInstance() {
        if (null == instance) {
            synchronized (JedisPoolUtils.class) {
                if (null == instance) {
                    instance = new JedisPoolUtils();
                }
            }
        }
        return instance;
    }

    private static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(1000);
        poolConfig.setTestOnBorrow(true);
        return poolConfig;
    }

    private JedisPoolUtils() {
         properties = readFileProperties();
        String host = properties.getProperty("LIST_REDIS_URI");
        int port = Integer.parseInt(properties.getProperty("REDIS_PORT"));
        int timeOut = Integer.parseInt(properties.getProperty("REDIS_TIME_OUT"));
        String password = properties.getProperty("REDIS_PASSWORD");
        int database = Integer.parseInt(properties.getProperty("REDIS_DATABASE"));
        jedisPool = new JedisPool(getJedisPoolConfig(), host, port, timeOut, password, database);
    }

    public boolean existKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.hexists(key, key)){
                return true;
            }
        } catch (Exception e) {
            logger.error("Error to get resource from the pool", e);
        }
        return false;
    }

    public void closePool() {
        if (null != jedisPool) {
            jedisPool.close();
            logger.warn("Closed pool...");
        }
    }
}
