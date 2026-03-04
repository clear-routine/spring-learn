package com.tt.config;

import com.tt.controller.ProductController;
import com.tt.repository.ProductRepository;
import com.tt.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Product 专用配置：每次 new 一个 ApplicationContext 并加载此配置，得到独立的「容器内的 singleton」
 */
@Configuration
public class ProductConfig {

    @Bean
    public ProductRepository productRepository() {

        return new ProductRepository();
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {

        ProductService service = new ProductService();
        service.setProductRepository(productRepository);
        return service;
    }

    @Bean
    public ProductController productController(ProductService productService) {

        ProductController controller = new ProductController();
        controller.setProductService(productService);
        return controller;
    }
}
