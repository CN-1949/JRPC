package test;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import site.RpcInstal;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class YamlTest {

    public static void main(String[] args) throws YamlException, FileNotFoundException {
        ClassLoader classLoader = YamlTest.class.getClassLoader();
        String path = classLoader.getResource("application.yml").getPath();
        YamlReader reader = new YamlReader(new FileReader(path));
        RpcInstal read = reader.read(RpcInstal.class);
    }
}