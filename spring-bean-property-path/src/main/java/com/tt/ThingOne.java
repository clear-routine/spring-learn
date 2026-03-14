package com.tt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThingOne {

    private Fred fred;

    @Override
    public String toString() {

        return "ThingOne{fred=" + fred + "}";
    }
}
