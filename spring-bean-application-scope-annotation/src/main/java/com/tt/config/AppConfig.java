package com.tt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.tt",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.tt\\.product\\..*")
)
public class AppConfig {
}
