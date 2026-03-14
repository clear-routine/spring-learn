package com.tt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bob {

    private String sammy;

    @Override
    public String toString() {

        return "Bob{sammy='" + sammy + "'}";
    }
}
