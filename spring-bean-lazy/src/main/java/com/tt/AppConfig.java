package com.tt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Java 配置类：演示 @Lazy 延迟初始化
 */
@Configuration
public class AppConfig {

    /** 默认预实例化：容器启动时立即创建 */
    @Bean
    public HeavyBean eagerBean() {
        return new HeavyBean("eagerBean(Java)");
    }

    /** @Lazy：延迟初始化，首次 getBean 时才创建 */
    @Bean
    @Lazy
    public HeavyBean lazyBean() {
        return new HeavyBean("lazyBean(Java)");
    }
}
