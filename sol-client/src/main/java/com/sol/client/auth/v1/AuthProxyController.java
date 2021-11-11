package com.sol.client.auth.v1;

import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.core.credential.response.CredentialResponse;
import ru.foodtechlab.lib.auth.integration.restapi.feign.credential.FeignCredentialServiceClient;
import ru.foodtechlab.lib.auth.integration.restapi.feign.credential.impl.FeignHTTPCredentialFacade;

@RestController
@Component
public class AuthProxyController {
    private final CredentialServiceFacade credentialServiceFacade;

    public AuthProxyController(CredentialServiceFacade credentialServiceFacade) {
        this.credentialServiceFacade = credentialServiceFacade;
    }

    @GetMapping(value = "/api/v1/credentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<SearchResult<CredentialResponse>> find(@ModelAttribute SearchFilters var1){
        SearchResult<CredentialResponse> result = credentialServiceFacade.find(var1);
        return SuccessApiResponse.of(result);
    }
}
