package com.sol.domain.solUser.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Setting {
    private Long defaultPointWeight = 25 * 60 * 1000L;
}
