package com.tt;

import java.util.UUID;

/**
 * 普通类，用于验证 Spring 单例作用域
 * 每个实例有唯一 ID，便于区分
 */
public class SharedService {

    private final String instanceId = UUID.randomUUID().toString().substring(0, 8);

    public String getInstanceId() {
        return instanceId;
    }
}
