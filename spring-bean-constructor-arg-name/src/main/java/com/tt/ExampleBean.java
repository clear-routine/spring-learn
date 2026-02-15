package com.tt;

/**
 * 验证：XML 中 value 本质是字符串，Spring 需 type 属性才能正确做类型转换和参数匹配
 *
 * 构造器：(int years, String ultimateAnswer)
 * - ref 引用 bean 时，Spring 知道类型，可以匹配
 * - value 简单值时，Spring 拿到的是字符串，需 type 显式指定才能匹配
 */
public class ExampleBean {

    /** 计算终极答案的年数 */
    private final int years;

    /** 生命、宇宙以及一切的终极答案 */
    private final String ultimateAnswer;

    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }

    @Override
    public String toString() {
        return "ExampleBean{years=" + years + ", ultimateAnswer='" + ultimateAnswer + "'}";
    }
}
