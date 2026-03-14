package com.tt;

import lombok.Getter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 原型 Bean：每次从容器获取都是新实例
 */
@Getter
@Component
@Scope("prototype")
public class PrototypeBean {

    private final String instanceId = UUID.randomUUID().toString().substring(0, 8);

    public void doWork() {

        System.out.println("  PrototypeBean 执行工作，实例 ID: " + instanceId);
    }
}
