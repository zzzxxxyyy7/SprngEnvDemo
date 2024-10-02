package com.example.springenvdemo.env;

import org.springframework.core.env.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertySourceDemo {
    public static void main(String[] args) throws IOException {

        // 从 .properties 文件加载属性
        Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
        PropertiesPropertySource propertySource = new PropertiesPropertySource("properties", properties);

        // 直接从 Resource 加载属性
        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        ResourcePropertySource resourcePropertySource = new ResourcePropertySource("resource", classPathResource);

        // 从 Map 里面加载属性
        Map<String , Object> map = new HashMap<>();
        map.put("name", "hello world");
        map.put("version" , "1.0");
        MapPropertySource mapPropertySource = new MapPropertySource("map", map);

        // 从操作系统环境变量里面加载属性
        Map mapEnv  = System.getenv();
        SystemEnvironmentPropertySource systemEnv = new SystemEnvironmentPropertySource("systemEnv", mapEnv);

        // 从命令行参数里面加载属性
        String[] myArgs = {"--appName=MyApplication", "--port=8080"};
        SimpleCommandLinePropertySource commandLinePropertySource = new SimpleCommandLinePropertySource(myArgs);

        CompositePropertySource compositePropertySource = new CompositePropertySource("composite");
        compositePropertySource.addPropertySource(propertySource);
        compositePropertySource.addPropertySource(resourcePropertySource);
        compositePropertySource.addPropertySource(mapPropertySource);
        compositePropertySource.addPropertySource(systemEnv);
        compositePropertySource.addPropertySource(commandLinePropertySource);

        for (PropertySource<?> source : compositePropertySource.getPropertySources()) {
            System.out.printf("Name: %-15s || Source: %s%n", source.getName(), source.getSource());
        }
    }
}
