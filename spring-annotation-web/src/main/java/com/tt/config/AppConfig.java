package com.tt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring 配置类：使用注解配置
 * 替代 application.xml
 * 
 * @Configuration 用于标明当前类是一个配置类，该类会被 Spring 自动管理，并注册为一个 Bean
 * @ComponentScan("com.tt") 用于指定组件扫描的范围，作用是扫描 com.tt 包及其子包下所有可以被注册为 Bean 的类
 */
@Configuration
@ComponentScan("com.tt")
public class AppConfig {
    // 使用 @ComponentScan 自动扫描并注册所有带注解的组件
}
