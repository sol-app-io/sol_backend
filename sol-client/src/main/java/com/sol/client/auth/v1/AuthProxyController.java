package com.sol.client.auth.v1;

import com.rcore.domain.commons.exception.NotFoundDomainException;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade;
import ru.foodtechlab.lib.auth.service.facade.credential.dto.requests.FindCredentialWithFiltersRequest;
import ru.foodtechlab.lib.auth.service.facade.credential.dto.requests.UpdateCredentialRequest;
import ru.foodtechlab.lib.auth.service.facade.credential.dto.responses.CredentialResponse;

@RestController
@Component
public class AuthProxyController {
    private final CredentialServiceFacade credentialServiceFacade;

    public AuthProxyController(CredentialServiceFacade credentialServiceFacade) {
        this.credentialServiceFacade = credentialServiceFacade;
    }

    @GetMapping(value = "/api/v1/credentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<SearchResult<CredentialResponse>> find(@ModelAttribute SearchFilters var1){
        FindCredentialWithFiltersRequest req = new FindCredentialWithFiltersRequest();
        SearchResult<CredentialResponse> result = credentialServiceFacade.find(req);
        return SuccessApiResponse.of(result);
    }

    @GetMapping(value = "/api/v1/credentials/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<CredentialResponse> findById(@PathVariable String id){
        CredentialResponse result = credentialServiceFacade.findById(id).orElseThrow(NotFoundDomainException::new);
        return SuccessApiResponse.of(result);
    }

    @PutMapping(value = "/api/v1/credentials/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<CredentialResponse> save(@PathVariable String id, @RequestBody UpdateCredentialRequest request){
        CredentialResponse result = credentialServiceFacade.update(id, request);
        return SuccessApiResponse.of(result);
    }
}
