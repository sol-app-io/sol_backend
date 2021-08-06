package com.sol.domain.base.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Icon {
    public enum Type{
        EMOJI
    }

    public static Icon of(String emoji){
        Icon icon = new Icon();
        icon.setData(emoji);
        icon.setType(Type.EMOJI);
        return icon;
    }

    private String data;
    private Type type = Type.EMOJI;
}
