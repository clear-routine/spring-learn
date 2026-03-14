package com.tt;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于演示 "" 与 null 的区别
 */
@Getter
@Setter
public class ExampleBean {

    private String email;

    public int getEmailLength() {

        return email.length();
    }
}
