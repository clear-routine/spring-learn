package com.tt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fred {

    private Bob bob;

    @Override
    public String toString() {

        return "Fred{bob=" + bob + "}";
    }
}
