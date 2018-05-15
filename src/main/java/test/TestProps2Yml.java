package test;


import tools.Props2YAML;
import java.io.InputStream;

/**
 * 将Properties文件转换成yml文件
 * Created by james.you on 2018/5/11.
 */
public class TestProps2Yml {
    public static void main(String[] args) {
        InputStream fileInputStream = TestProps2Yml.class.getResourceAsStream("/properties/redis.properties");
        Props2YAML props2YAML = Props2YAML.fromInputStream(fileInputStream);
        String yml = props2YAML.convert();
        System.out.println(yml);
        /*String filePath = TestProps2Yml.class.getResource("/properties/redis.properties").getPath();
        System.out.println(filePath);*/
    }
}
