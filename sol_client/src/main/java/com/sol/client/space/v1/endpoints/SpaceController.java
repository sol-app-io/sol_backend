package com.sol.client.space.v1.endpoints;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.base.controller.BaseApiController;
import com.sol.client.space.v1.api.request.SpaceRequest;
import com.sol.client.space.v1.api.response.SpaceResponse;
import com.sol.client.space.v1.mapper.SpaceApiMapper;
import com.sol.client.space.v1.routes.SpaceRoute;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.space.config.SpaceConfig;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.usecases.CreateSpaceUseCase;
import com.sol.domain.space.usecases.FindSpaceByOwnerIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class SpaceController extends BaseApiController {

    private final SpaceConfig spaceConfig;
    private final SolUserConfig solUserConfig;
    private final SpaceApiMapper mapper = new SpaceApiMapper();

    @Override
    protected SolUserConfig solUserConfig() {
        return solUserConfig;
    }

    @PostMapping(value = SpaceRoute.ROOT)
    public SuccessApiResponse<SpaceResponse> create(
            @RequestBody SpaceRequest spaceRequest,
            @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal) {

        SpaceEntity spaceEntity = spaceConfig.createSpaceUseCase().execute(
                CreateSpaceUseCase.InputValues.builder()
                        .title(spaceRequest.getTitle())
                        .icon(Icon.of(spaceRequest.getIcon()))
                        .ownerId(this.getCurrentUser(credentialPrincipal).getId())
                        .type(SpaceEntity.Type.DEFAULT)
                        .build()
        ).getEntity();

        return SuccessApiResponse.of(mapper.mapper(spaceEntity));
    }

    @GetMapping(value = SpaceRoute.ROOT)
    public SuccessApiResponse<List<SpaceResponse>> mySpaces(
            @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal) {

        List<SpaceEntity> spaceEntities = spaceConfig.findSpaceByOwnerIdUseCase().execute(
                FindSpaceByOwnerIdUseCase.InputValues.of(this.getCurrentUser(credentialPrincipal).getId())
        ).getEntity();

        return SuccessApiResponse.of(spaceEntities.stream().map(mapper::mapper).collect(Collectors.toList()));
    }


}
