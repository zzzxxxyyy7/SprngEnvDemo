package com.example.springenvdemo.env;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class PropertySourcesDemo {
    public static void main(String[] args) {
        // 创建 MutablePropertySources 对象
        MutablePropertySources propertySources = new MutablePropertySources();

        Map<String , Object> map1 = new HashMap<>();
        map1.put("name", "map1");
        map1.put("version" , "1.0");
        PropertySource<?> mapPropertySource1 = new MapPropertySource("map1", map1);

        Map<String , Object> map2 = new HashMap<>();
        map2.put("name", "map2");
        map2.put("version" , "2.0");
        PropertySource<?> mapPropertySource2 = new MapPropertySource("map2", map2);

        propertySources.addFirst(mapPropertySource1);
        propertySources.addLast(mapPropertySource2);

        System.out.println("打印属性源");
        for (PropertySource<?> ps : propertySources) {
            System.out.printf("Name: %-10s || Source: %s%n", ps.getName(), ps.getSource());
        }
        System.out.println();

        // 替换属性源
        Map<String , Object> map3 = new HashMap<>();
        map3.put("name", "map3");
        map3.put("version" , "3.0");
        PropertySource<?> mapPropertySource3 = new MapPropertySource("map3", map3);
        propertySources.replace("map1", mapPropertySource3);

        // 打印
        System.out.println("打印替换后的属性源");
        for (PropertySource<?> ps : propertySources) {
            System.out.printf("Name: %-10s || Source: %s%n", ps.getName(), ps.getSource());
        }
        System.out.println();

        // 检查是否包含属性源
        System.out.println("是否包含属性源 map2: " + propertySources.contains("map2"));

        // 移除属性源
        System.out.println("移除属性源 map2: " + propertySources.remove("map2"));

        // 再次检查是否包含属性源
        System.out.println("删除后是否包含属性源 map2: " + propertySources.contains("map2"));

    }
}
