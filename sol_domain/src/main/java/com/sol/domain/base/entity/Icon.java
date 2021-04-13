package com.sol.domain.base.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Icon {
    public enum Type{
        EMOJI
    }

    private String data;
    private Type type = Type.EMOJI;
}
