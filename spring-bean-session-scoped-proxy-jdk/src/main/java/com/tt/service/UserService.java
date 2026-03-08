package com.tt.service;

/**
 * UserService 接口：JDK 动态代理依赖接口，注入时必须通过接口类型引用
 */
public interface UserService {

    String getUser();

    String getInstanceId();
}
