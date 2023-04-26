package com.sol.client.slot.v1;

import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.slot.v1.request.CreateSlotRequest;
import com.sol.client.slot.v1.request.SearchSlotRequest;
import com.sol.client.slot.v1.request.UpdateSlotRequest;
import com.sol.client.slot.v1.response.SlotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import java.util.List;

@RestController
@Tag(name = "Slot", description = "Slot")
public interface SlotResource {


    @Operation(summary = "create")
    @PostMapping(value = SlotRoutes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SlotResponse> create(@RequestBody CreateSlotRequest request, @CurrentCredential CredentialPrincipal credentialPrincipal);


    @GetMapping(value = SlotRoutes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SlotResponse> get(@PathVariable("id") String id, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PutMapping(value = SlotRoutes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SlotResponse> put(@PathVariable("id") String id, @RequestBody UpdateSlotRequest request, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @DeleteMapping(value = SlotRoutes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<String> delete(@PathVariable("id") String id, @CurrentCredential CredentialPrincipal credentialPrincipal);


    @GetMapping(value = SlotRoutes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SearchApiResponse<SlotResponse>> find(
            @ModelAttribute SearchSlotRequest request,
    @CurrentCredential CredentialPrincipal credentialPrincipal);


}
