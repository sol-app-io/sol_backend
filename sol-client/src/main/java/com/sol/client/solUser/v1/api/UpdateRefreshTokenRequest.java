package com.sol.client.solUser.v1.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRefreshTokenRequest {
    private String refreshToken;
}
