package com.sol.client.solUser.v1.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpEmailRequest {
    private String email;
    private String password;
}
