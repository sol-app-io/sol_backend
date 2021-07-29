package com.sol.client.solUser.v1.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpResponse {
    private String id;
    private String username;
    //private String accessToken;
    //private String refreshToken;
}
