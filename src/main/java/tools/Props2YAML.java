package tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ArrayProcessor;
import utils.PropertyTree;
import utils.TreeBuilder;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.util.Properties;

/**
 * properties config convert to yaml
 * this class is not threadsafe，if you want，rewrite is easy
 * convertProps2Yaml method is threadsafe，Suggested use it
 * Created by james.you on 2018/5/14.
 * */
public class Props2YAML {

    private final static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final Properties properties = new Properties();

    public Props2YAML(String source) {
        try {
            properties.load(new StringReader(source));
        } catch (IOException e) {
            reportError(e);
        }
    }

    public Props2YAML(File file) {
        try (InputStream input = new FileInputStream(file)) {
            properties.load(input);
        } catch (IOException e) {
            reportError(e);
        }
    }

    public Props2YAML(InputStream input){
        try{
            properties.load(input);
        } catch (IOException e) {
            reportError(e);
        }
    }

    public static Props2YAML fromContent(String content) {
        return new Props2YAML(content);
    }

    public static Props2YAML fromFile(File file) {
        return new Props2YAML(file);
    }

    public static Props2YAML fromFile(Path path) {
        return new Props2YAML(path.toFile());
    }

    public static Props2YAML fromInputStream(InputStream input){return new Props2YAML(input);}

    public String convert(boolean useNumericKeysAsArrayIndexes) {
        PropertyTree tree = new TreeBuilder(properties,useNumericKeysAsArrayIndexes).build();
        tree = new ArrayProcessor(tree).apply();
        return tree.toYAML();
    }
    /**
     *
     * */
    public String convert() {
        return convert(true);
    }

    /**
     * properties convert to yaml
     * @param propertiesInputStream
     * @param useNumericKeysAsArrayIndexes
     * @return yaml（String format content）
     */
    public static String convertProps2Yaml(InputStream propertiesInputStream,boolean useNumericKeysAsArrayIndexes) throws IOException{
        Properties properties = new Properties();
        try {
            properties.load(propertiesInputStream);
        } catch (IOException e) {
            throw e;
        }
        PropertyTree tree = new TreeBuilder(properties,useNumericKeysAsArrayIndexes).build();
        tree = new ArrayProcessor(tree).apply();
        return tree.toYAML();
    }

    private void reportError(IOException e) {
        LOG.error("Conversion failed", e);
    }

}

