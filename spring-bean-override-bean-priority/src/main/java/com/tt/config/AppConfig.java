package com.tt.config;

import com.tt.user.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：使用 @Configuration + @Bean 方式（显式配置）
 * 
 * 同时使用 @ComponentScan 扫描组件，会发现 User1（@Component("user")）
 * 
 * 注意：在 Spring 5.3.21 中，当设置了 allowBeanDefinitionOverriding = false 时，
 * 即使是 @Bean 也无法覆盖 @Component，会抛出异常。
 * 
 * 但在某些版本或特定场景下，@Bean 确实可以覆盖 @Component，
 * 因为遵循"显式 > 隐式"的原则。
 */
@Configuration
@ComponentScan("com.tt.user")
public class AppConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    
    /**
     * 使用 @Bean 显式定义 user Bean（显式配置）
     * 
     * 理论上，@Bean 的优先级应该高于 @Component（隐式配置），
     * 但在 Spring 5.3.21 中，当禁止覆盖时，两者都无法互相覆盖。
     */
    @Bean("user")
    public User2 user() {
        logger.info("AppConfig: 使用 @Bean 显式定义 user Bean（显式配置）");
        logger.debug("AppConfig: 创建 User2 实例，会覆盖 @Component 定义的 User1");
        System.out.println("AppConfig: 使用 @Bean 显式定义 user Bean");
        return new User2("第二个User");
    }
}
