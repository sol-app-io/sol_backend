package com.sol.client.solUser.v1.enpoints;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.sol.client.solUser.v1.api.SignUpEmailRequest;
import com.sol.client.solUser.v1.api.SignUpResponse;
import com.sol.client.solUser.v1.mapper.SolUserMapper;
import com.sol.client.solUser.v1.routes.SignUpRoutes;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.usecases.SignUpByEmailSolUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SignUpController {

    private final SolUserConfig solUserConfig;
    private final SolUserMapper mapper = new SolUserMapper();

    @PostMapping(value = SignUpRoutes.EMAIL)
    public SuccessApiResponse<SignUpResponse> email(@RequestBody SignUpEmailRequest request) {
        return SuccessApiResponse.of(
                mapper.mapper(
                        solUserConfig
                                .signUpByEmailSolUserUseCase()
                                .execute(
                                        SignUpByEmailSolUserUseCase.InputValues.builder()
                                                .email(request.getEmail())
                                                .password(request.getPassword())
                                                .build()
                                ).getEntity()));
    }
}
