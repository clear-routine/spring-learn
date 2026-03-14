package com.tt;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 演示 props、list、map、set 四种集合注入
 */
@Data
public class ComplexObject {

    private Properties adminEmails;
    private List<Object> someList;
    private Map<Object, Object> someMap;
    private Set<Object> someSet;
    private Map<String, Object> complexMap;   // 复杂 Map：value 为 list
    private List<Object> complexList;          // 复杂 List：元素为 map
    private Map<String, Float> accounts;        // 强类型 Map，XML 字符串 → Float 自动转换
}
