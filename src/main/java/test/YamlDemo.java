package test;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * org.yaml.snakeyaml.Yaml 常规用法
 * 1. 打印yaml解析的Map结果，类似如下：
 *      direct_port：6379
        direct_testOnBorrow：false
        direct_testWhileIdle：true
        direct_timeBetweenEvictionRunsMillis：100000001
        direct_timeout：1000
        spring：{application={admin={enabled=false}, name=MyProject}}

 * Created by james.you on 2018/5/15.
 */
public class YamlDemo {
    public static void main(String[] args) {
        InputStream redis_conf = YamlDemo.class.getResourceAsStream("/properties/redis_conf.yml");
        Yaml yaml = new Yaml();
        //1. 将yaml文档，根据官方文档语法结构说明，解析成Map
        Map<String, Object> map = (Map<String, Object>) yaml.load(redis_conf);
        //2. 遍历Map
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String nodeName = iterator.next();
            System.out.println(nodeName+"："+map.get(nodeName));
        }
    }
}
