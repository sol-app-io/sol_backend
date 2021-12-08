package com.sol.client.viewUser.v1;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.viewUser.v1.response.ViewUserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "View User API", description = "View ViewUserController docs")
@RestController
public interface ViewUserResource {

    @ApiOperation("Получение документа в ресурсе")
    @GetMapping(value =  Routes.MY_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<ViewUserResponse>> myRoot(@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

}