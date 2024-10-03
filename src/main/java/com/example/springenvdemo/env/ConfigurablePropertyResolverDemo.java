package com.example.springenvdemo.env;

import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

import java.util.HashMap;
import java.util.Map;

public class ConfigurablePropertyResolverDemo {
    public static void main(String[] args) {

        // 创建 属性源
        Map<String, Object> map = new HashMap<>();
        map.put("name", "map");
        map.put("version", "1.0");
        MapPropertySource mapPropertySource = new MapPropertySource("map", map);

        // 创建 PropertySource
        MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addLast(mapPropertySource);

        // 创建 PropertyResolver
        PropertySourcesPropertyResolver propertySourcesPropertyResolver = new PropertySourcesPropertyResolver(propertySources);

        // 设置转换服务
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        propertySourcesPropertyResolver.setConversionService(defaultConversionService);

        // 设置占位符前后缀
        propertySourcesPropertyResolver.setPlaceholderPrefix("${");
        propertySourcesPropertyResolver.setPlaceholderSuffix("}");

        // 设置默认值分隔符
        propertySourcesPropertyResolver.setValueSeparator(":");

        // 设置未解析占位符的处理方式
        propertySourcesPropertyResolver.setIgnoreUnresolvableNestedPlaceholders(true);

        // 设置并验证必需的属性
        propertySourcesPropertyResolver.setRequiredProperties("name", "version");
        propertySourcesPropertyResolver.validateRequiredProperties();

        // 读取属性
        String Name = propertySourcesPropertyResolver.getProperty("name");
        String Version = propertySourcesPropertyResolver.getProperty("version", String.class, "Unknown Version");
        System.out.println("获取属性 name: " + Name);
        System.out.println("获取属性 version: " + Version);
    }
}
