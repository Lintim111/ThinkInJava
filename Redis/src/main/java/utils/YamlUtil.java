package utils;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlUtil {
    public static <T> T LoadFromYaml(T clazzInstance, InputStream yamlStream){
        Constructor constructor = new Constructor(clazzInstance.getClass());
        TypeDescription description = new TypeDescription(clazzInstance.getClass());

        constructor.addTypeDescription(description);

        Yaml yaml = new Yaml(constructor);
        return (T)yaml.load(yamlStream);
    }

    public static InputStream YamlStream(String path){
        return YamlUtil.class.getClassLoader().getResourceAsStream(path);
    }
}
