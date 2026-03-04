package com.tt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Product 专用配置：每次 new 一个 ApplicationContext 并加载此配置，得到独立的「容器内的 singleton」
 */
@Configuration
@ComponentScan("com.tt.product")
public class ProductConfig {
}
