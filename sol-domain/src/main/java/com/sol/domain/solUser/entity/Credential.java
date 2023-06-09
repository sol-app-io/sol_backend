package com.sol.domain.solUser.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Credential {
    public enum Type{
        EMAIL,APPLE_ID,FACEBOOK,PHONE
    }

    private String credentialId;
    private Type type;
}
