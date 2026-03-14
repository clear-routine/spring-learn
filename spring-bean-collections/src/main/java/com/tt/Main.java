package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 props、list、map、set 四种集合注入
 * 以及 map/list 的复杂嵌套（value 可为 bean、ref、list、map 等）
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        ComplexObject obj = context.getBean("complexObject", ComplexObject.class);

        System.out.println("=== 1. props (Properties) ===");
        System.out.println(obj.getAdminEmails());
        System.out.println();

        System.out.println("=== 2. list (List) ===");
        System.out.println(obj.getSomeList());
        System.out.println();

        System.out.println("=== 3. map (Map) ===");
        System.out.println(obj.getSomeMap());
        System.out.println();

        System.out.println("=== 4. set (Set) ===");
        System.out.println(obj.getSomeSet());
        System.out.println();

        System.out.println("=== 5. 复杂 Map（value 为 list/ref）===");
        obj.getComplexMap().forEach((k, v) -> System.out.println("  " + k + " = " + v));
        System.out.println();

        System.out.println("=== 6. 复杂 List（元素为 map）===");
        for (int i = 0; i < obj.getComplexList().size(); i++) {
            Object item = obj.getComplexList().get(i);
            System.out.println("  [" + i + "] " + item);
        }
        System.out.println();

        System.out.println("=== 7. 强类型 Map<String, Float>（XML 字符串 → Float 自动转换）===");
        obj.getAccounts().forEach((k, v) ->
                System.out.println("  " + k + " = " + v + " (" + v.getClass().getSimpleName() + ")"));
    }
}
