package com.sol.client.slot.v1;

import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.slot.v1.request.CreateSlotRequest;
import com.sol.client.slot.v1.request.SearchSlotRequest;
import com.sol.client.slot.v1.request.UpdateSlotRequest;
import com.sol.client.slot.v1.response.SlotResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.naming.directory.SearchResult;
import java.util.List;

@RestController
public interface SlotResource {

    @ApiOperation("PostMapping")
    @PostMapping(value = SlotRoutes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SlotResponse> create(@RequestBody CreateSlotRequest request, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);


    @ApiOperation("GetMapping")
    @GetMapping(value = SlotRoutes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SlotResponse> get(@PathVariable("id") String id, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("PutMapping")
    @PutMapping(value = SlotRoutes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SlotResponse> put(@PathVariable("id") String id, @RequestBody UpdateSlotRequest request, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("DeleteMapping")
    @DeleteMapping(value = SlotRoutes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<String> delete(@PathVariable("id") String id, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("Find")
    @GetMapping(value = SlotRoutes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SearchApiResponse<SlotResponse>> find(
            @ModelAttribute SearchSlotRequest request,
            @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);


}
