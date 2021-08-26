package vn.vnpay.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.vnpay.constant.SystemConstant;
import vn.vnpay.constant.ValidateConstant;
import vn.vnpay.exception.CustomException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ReadConfigYaml {

    private static final Logger logger = LogManager.getLogger(ReadConfigYaml.class);

    public static String findAccessKeyByAppId(String nameAppId) {
        Yaml yaml = new Yaml();
        Map map = null;
        File file = new File(SystemConstant.PATH_FILE_YAML);
        try (InputStream input = new FileInputStream(file)) {
            Object nameAndAccessKey = yaml.load(input);
            logger.info("Name and access key from path : {}", nameAndAccessKey);
            map = (Map) nameAndAccessKey;
        } catch (IOException e) {
            logger.error("Can not found the file ", e);
        }
        if (map.get(nameAppId) == null){
            return null;
        } else {
            return map.get(nameAppId).toString();
        }
    }
}

