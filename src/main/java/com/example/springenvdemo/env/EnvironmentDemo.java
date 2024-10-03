package com.example.springenvdemo.env;

import org.springframework.core.env.StandardEnvironment;

public class EnvironmentDemo {
    public static void main(String[] args) {
        // 设置系统属性
        System.setProperty("spring.profiles.default", "dev");
        System.setProperty("spring.profiles.active", "test");

        // 创建 Environment，创建过程中会自动读取 JVM 参数和系统属性并封装成 PropertySources
        StandardEnvironment standardEnvironment = new StandardEnvironment();

        // 获取 Java 版本
        System.out.println("java.version = " + standardEnvironment.getProperty("java.version"));

        // 获取到当前激活的配置文件（profiles）
        String[] activeProfiles = standardEnvironment.getActiveProfiles();
        System.out.println("activeProfiles = " + String.join(",", activeProfiles));

        // 检查是否激活了指定的配置文件。这里检查的是 'test' 配置文件
        boolean isDevProfileActive = standardEnvironment.matchesProfiles("test");
        System.out.println("acceptsProfiles('test'): " + isDevProfileActive);
    }
}
