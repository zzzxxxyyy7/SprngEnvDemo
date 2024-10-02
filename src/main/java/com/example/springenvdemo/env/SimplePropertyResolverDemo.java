package com.example.springenvdemo.env;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

import java.util.HashMap;
import java.util.Map;

public class SimplePropertyResolverDemo {
    public static void main(String[] args) {

        // 构建 MapPropertySource 属性源
        Map<String , Object> map = new HashMap<>();
        map.put("name" , "simplePropertyResolver");
        map.put("version" , "1.0");
        MapPropertySource simplePropertyResolver = new MapPropertySource("map"  , map);

        Map<String , Object> map2 = new HashMap<>();
        map.put("name" , "simplePropertyResolver3");
        map.put("version" , "1.0");
        MapPropertySource simplePropertyResolver2 = new MapPropertySource("map2"  , map2);

        // 构建 PropertySources 管理属性源
        MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addLast(simplePropertyResolver);
        propertySources.addLast(simplePropertyResolver2);

        // 交给 Resolver 解析
        PropertySourcesPropertyResolver propertySourcesPropertyResolver = new PropertySourcesPropertyResolver(propertySources);

        // 检查属性是否存在，如果没有返回 Null
        String appName = propertySourcesPropertyResolver.getProperty("name");
        String appVersion = propertySourcesPropertyResolver.getProperty("version");
        System.out.println("获取属性 name: " + appName);
        System.out.println("获取属性 version: " + appVersion);

        // 检查属性是否存在
        boolean containsReleaseDate = propertySourcesPropertyResolver.containsProperty("releaseDate");
        System.out.println("是否包含 'releaseDate' 属性: " + containsReleaseDate);

        // 带默认值的属性获取
        String appReleaseDate = propertySourcesPropertyResolver.getProperty("releaseDate", "2023-11-30");
        System.out.println("带默认值的属性获取 releaseDate : " + appReleaseDate);

        // 获取必需属性，如果没有抛出异常
        String requiredAppName = propertySourcesPropertyResolver.getRequiredProperty("name");
        System.out.println("获取必需属性 name: " + requiredAppName);

    }
}
