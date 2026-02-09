package com.tt.controller;

import com.tt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 产品控制器：处理产品相关的业务逻辑
 * 
 * 在控制器类上添加对应的 @Controller 注解，并在属性上使用 @Autowired 注解完成依赖注入，
 * 这样就可以彻底替代基于 XML 配置文件的对象关系管理和配置方式，使整体代码结构更加清晰、简洁
 */
@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 获取产品信息
     * @return 产品信息
     */
    public String getProduct() {
        return productService.getProduct();
    }
}
