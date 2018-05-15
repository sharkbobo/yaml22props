package test;


import tools.Yaml2PROPS;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * 测试 yaml文件，转换成properties
 * Created by james.you on 2018/5/15.
 */
public class TestYaml2Props {
    public static void main(String[] args) {
        InputStream yamlInputStream = TestYaml2Props.class.getResourceAsStream("/properties/redis_conf.yml");
        List<String> envs = Arrays.asList("dev");
        try {
            Properties properties = Yaml2PROPS.fromYamlInputStream(yamlInputStream,envs);
            Iterator iterator = properties.keySet().iterator();
            while (iterator.hasNext()){
                Object key = iterator.next();
                System.out.println(key+"="+properties.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
