package com.sol.client.space.v1.endpoints;

import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.base.controller.BaseApiController;
import com.sol.client.space.v1.api.request.ChangeSortSpaceRequest;
import com.sol.client.space.v1.api.request.SpaceEditRequest;
import com.sol.client.space.v1.api.request.SpaceRequest;
import com.sol.client.space.v1.api.response.SpaceResponse;
import com.sol.client.space.v1.mapper.SpaceApiMapper;
import com.sol.client.space.v1.routes.SpaceRoute;
import com.sol.client.task.v1.mappers.TaskResponseMapper;
import com.sol.client.task.v1.response.TaskResponse;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.space.config.SpaceConfig;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.exceptions.HasNoAccessToSpaceException;
import com.sol.domain.space.usecases.*;
import com.sol.domain.task.config.TaskConfig;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.usecases.FindTaskBySpaceIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class SpaceController extends BaseApiController {

    private final SpaceConfig spaceConfig;
    private final SolUserConfig solUserConfig;
    private final TaskConfig taskConfig;
    private final SpaceApiMapper mapper = new SpaceApiMapper();
    private final TaskResponseMapper taskMapper = new TaskResponseMapper();

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

    @GetMapping(value = SpaceRoute.SINGLETON)
    public SuccessApiResponse<SpaceResponse> singleton(
            @PathVariable String id,
            @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal) {

        SpaceEntity spaceEntity = spaceConfig.findSpaceByIdUseCase().execute(
                IdInputValues.of(id)
        ).getEntity().get();

        SolUserEntity solUserEntity = solUserConfig
                .meUseCase().execute(
                        MeUseCase.InputValues
                                .builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()).getEntity();

        if (spaceEntity.checkAccess(solUserEntity.getId()) == false) throw new HasNoAccessToSpaceException();

        SpaceResponse spaceResponse = mapper.mapper(spaceEntity);

        List<TaskResponse> taskEntities = taskConfig
                .findTaskBySpaceIdUseCase().execute(
                        FindTaskBySpaceIdUseCase.InputValues
                                .builder().spaceId(id).build()).getEntity().stream().map(TaskResponseMapper::map).collect(Collectors.toList());

        spaceResponse.setTasks(taskEntities);

        return SuccessApiResponse.of(spaceResponse);
    }

    @PatchMapping(value = SpaceRoute.SINGLETON)
    public SuccessApiResponse<SpaceResponse> singleton(
            @PathVariable String id,
            @RequestBody SpaceEditRequest request,
            @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal) {

        SolUserEntity solUserEntity = solUserConfig
                .meUseCase().execute(
                        MeUseCase.InputValues
                                .builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()).getEntity();

        SpaceEntity spaceEntity = spaceConfig
                .updateSpaceUseCase()
                .execute(
                        UpdateSpaceUseCase.InputValues.of(
                                id,
                                request.getTitle(),
                                Icon.of(request.getIcon()),
                                solUserEntity.getId()))
                .getEntity();

        return SuccessApiResponse.of(mapper.mapper(spaceEntity));
    }

    @PostMapping(value = SpaceRoute.SORT_CHANGE)
    public SuccessApiResponse<List<SpaceResponse>> changeSort(
            @RequestBody ChangeSortSpaceRequest request,
            @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal
    ) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(
                MeUseCase.InputValues
                        .builder()
                        .credentialId(credentialPrincipal.getId())
                        .build()
        ).getEntity();

        List<SpaceEntity> spaceEntities = spaceConfig.changeSpaceSortUseCase().execute(
                ChangeSpaceSortUseCase.InputValues.of(request.getSpaces(), solUserEntity.getId())
        ).getEntity();

        return SuccessApiResponse.of(spaceEntities.stream().map(mapper::mapper).collect(Collectors.toList()));
    }

}
