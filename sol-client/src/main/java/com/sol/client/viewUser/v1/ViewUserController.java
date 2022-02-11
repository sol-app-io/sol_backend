package com.sol.client.viewUser.v1;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.sol.client.viewUser.v1.mappers.ViewUserResponseMapper;
import com.sol.client.viewUser.v1.request.*;
import com.sol.client.viewUser.v1.response.TaskInViewResponse;
import com.sol.client.viewUser.v1.response.ViewUserResponse;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.taskInView.config.TaskInViewConfig;
import com.sol.domain.taskInView.usecases.CreateTaskInViewUseCase;
import com.sol.domain.taskInView.usecases.DeleteTaskInViewUseCase;
import com.sol.domain.taskInView.usecases.FindByTaskUseCase;
import com.sol.domain.taskInView.usecases.FindByViewUseCase;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.config.ViewTemplateConfig;
import com.sol.domain.viewUser.config.ViewUserConfig;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.usecases.*;
import com.sol.domain.viewsSort.config.ViewsSortConfig;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.usecases.HideViewInViewsSortUseCase;
import com.sol.domain.viewsSort.usecases.ShowViewInViewsSortUseCase;
import com.sol.domain.viewsSort.usecases.UpdateViewsSortUseCase;
import com.sol.infrastructure.database.mongo.base.ObjectIdHelper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("viewUserControllerV1")
public class ViewUserController implements ViewUserResource {
    private final UseCaseExecutor useCaseExecutor;
    private final ViewTemplateConfig viewTemplateConfig;
    private final ViewUserConfig viewUserConfig;
    private final ViewsSortConfig viewsSortConfig;
    private final TaskInViewConfig taskInViewConfig;
    private final SolUserConfig solUserConfig;
    private final ViewUserResponseMapper mapper = new ViewUserResponseMapper();
    private final ViewUserIdGenerator viewUserIdGenerator;

    @Override
    public SuccessApiResponse<List<ViewUserResponse>> myRoot(CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();

        return SuccessApiResponse.of(
                useCaseExecutor.execute(
                        viewUserConfig.findViewBySolUserUseCase(),
                        FindViewBySolUserUseCase.InputValues.of(solUserEntity.getId()),
                        o -> o.getEntity().stream().map(mapper::map).collect(Collectors.toList())
                )
        );
    }

    @Override
    public SuccessApiResponse<List<ViewUserResponse>> all(CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();

        return SuccessApiResponse.of(
                useCaseExecutor.execute(
                        viewUserConfig.findAllViewBySolUserUseCase(),
                        FindAllViewBySolUserUseCase.InputValues.of(solUserEntity.getId()),
                        o -> o.getEntity().stream().map(mapper::map).collect(Collectors.toList())
                )
        );
    }

    @Override
    public SuccessApiResponse<List<TaskInViewResponse>> findByTask(String taskId, CredentialPrincipal credentialPrincipal) {
        return SuccessApiResponse.of(
                useCaseExecutor.execute(
                        taskInViewConfig.findByTaskUseCase(),
                        FindByTaskUseCase.InputValues.of(taskId),
                        o -> o.getEntity().stream().map(mapper::map).collect(Collectors.toList())
                )
        );
    }

    @Override
    public SuccessApiResponse<List<TaskInViewResponse>> findTasksByView(String viewId, CredentialPrincipal credentialPrincipal) {
        return SuccessApiResponse.of(
                useCaseExecutor.execute(
                        taskInViewConfig.findByViewUseCase(),
                        FindByViewUseCase.InputValues.of(viewId),
                        o -> o.getEntity().stream().map(mapper::map).collect(Collectors.toList())
                )
        );
    }

    @Override
    public SuccessApiResponse<TaskInViewResponse> addTaskToView(CreateTaskInViewRequest request, CredentialPrincipal credentialPrincipal) {
        return SuccessApiResponse.of(
                useCaseExecutor.execute(
                        taskInViewConfig.createTaskInViewUseCase(),
                        CreateTaskInViewUseCase.InputValues.of(request.getViewId(), request.getTaskId(), null),
                        o -> mapper.map(o.getEntity())
                )
        );
    }

    @Override
    public SuccessApiResponse<Boolean> show(String id, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();


        useCaseExecutor.execute(
                viewUserConfig.showViewInViewsSortUseCase(),
                ShowViewInViewsSortUseCase.InputValues.of(solUserEntity.getId(), id)
        );

        return SuccessApiResponse.of(true);
    }

    @Override
    public SuccessApiResponse<Boolean> hide(String id, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();


        useCaseExecutor.execute(
                viewUserConfig.hideViewInViewsSortUseCase(),
                HideViewInViewsSortUseCase.InputValues.of(solUserEntity.getId(), id)
        );

        return SuccessApiResponse.of(true);
    }

    @Override
    public SuccessApiResponse<String> delete(String taskId, String viewId, CredentialPrincipal credentialPrincipal) {
        useCaseExecutor.execute(
                taskInViewConfig.deleteTaskInViewUseCase(),
                DeleteTaskInViewUseCase.InputValues.of(viewId, taskId)
        );
        return SuccessApiResponse.of("Ok");
    }

    @Override
    public SuccessApiResponse<ViewUserResponse> updateView(String id, UpdateUserViewRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();

        View view = new View();
        view.setIcon(request.getIcon());
        view.setTitle(request.getTitle());
        view.setDescription(request.getDescription());
        view.setAddedType(request.getAddedType());
        view.setDisplayMode(request.getDisplayMode());
        view.setSortType(request.getSortType());
        view.setViewType(request.getViewType());
        view.setParams(null);

        ViewUserEntity viewUserEntity = useCaseExecutor.execute(
                viewUserConfig.updateViewUserUseCase(),
                UpdateViewUserUseCase.InputValues.of(id, solUserEntity.getId(), view),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(mapper.map(viewUserEntity));
    }

    @Override
    public SuccessApiResponse<ViewUserResponse> updateAllView(String id, UpdateUserViewFullRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();

        View view = new View();
        view.setIcon(request.getIcon());
        view.setTitle(request.getTitle());
        view.setDescription(request.getDescription());
        view.setAddedType(request.getAddedType());
        view.setDisplayMode(request.getDisplayMode());
        view.setSortType(request.getSortType());
        view.setViewType(request.getViewType());
        view.setParams(request.getParams());

        for (View.Params params : request.getParams()) {
            if (ObjectIdHelper.mapOrDie(params.getId()) == null) {
                params.setId((new ObjectId()).toString());
            }
        }

        ViewUserEntity viewUserEntity = useCaseExecutor.execute(
                viewUserConfig.updateViewUserUseCase(),
                UpdateViewUserUseCase.InputValues.of(id, solUserEntity.getId(), view),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(mapper.map(viewUserEntity));
    }

    @Override
    public SuccessApiResponse<ViewUserResponse> paramAdd(String id, ViewParamRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();


        ViewUserEntity viewUserEntity = useCaseExecutor.execute(
                viewUserConfig.addParamToViewUserUseCase(),
                AddParamToViewUserUseCase.InputValues.of(id, solUserEntity.getId(), request.getType(), request.getValueString(), request.getValueDate(), request.getValueBool()),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(mapper.map(viewUserEntity));
    }

    @Override
    public SuccessApiResponse<ViewUserResponse> paramEdit(String id, String paramId, ViewParamRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();


        ViewUserEntity viewUserEntity = useCaseExecutor.execute(
                viewUserConfig.editParamToViewUserUseCase(),
                EditParamToViewUserUseCase.InputValues.of(
                        id,
                        solUserEntity.getId(), paramId,
                        request.getType(),
                        request.getValueString(),
                        request.getValueDate(),
                        request.getValueBool()),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(mapper.map(viewUserEntity));
    }

    @Override
    public SuccessApiResponse<ViewUserResponse> paramDelete(String id, String paramId, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();


        ViewUserEntity viewUserEntity = useCaseExecutor.execute(
                viewUserConfig.deleteParamToViewUserUseCase(),
                DeleteParamToViewUserUseCase.InputValues.of(
                        id,
                        solUserEntity.getId(),
                        paramId),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(mapper.map(viewUserEntity));
    }

    @Override
    public SuccessApiResponse<Boolean> changeSort(ChangeViewSortRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();


        useCaseExecutor.execute(
                viewsSortConfig.updateViewsSortUseCase(),
                UpdateViewsSortUseCase.InputValues.of(
                        solUserEntity.getId(),
                        ViewsSortEntity.Type.ROOT,
                        request.getViews()),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(true);
    }

    @Override
    public SuccessApiResponse<Boolean> delete(String id, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();


        useCaseExecutor.execute(
                viewUserConfig.deleteViewUserUseCase(),
                DeleteViewUserUseCase.InputValues.of(
                        id,
                        solUserEntity.getId())
        );

        return SuccessApiResponse.of(true);
    }

    @Override
    public SuccessApiResponse<ViewUserResponse> create(CreateViewUserRequest request, CredentialPrincipal credentialPrincipal) {

        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();

        View view = new View();
        view.setIcon(request.getIcon());
        view.setTitle(request.getTitle());
        view.setDescription(request.getDescription());
        view.setAddedType(request.getAddedType());
        view.setDisplayMode(request.getDisplayMode());
        view.setSortType(request.getSortType());
        view.setViewType(request.getViewType());
        view.setParams(request.getParams());

        if (request.getParams() != null) {
            for (View.Params params : request.getParams()) {
                params.setId(viewUserIdGenerator.generate());
            }
        } else {
            view.setParams(new ArrayList<>());
        }

        ViewUserEntity viewUserEntity = useCaseExecutor.execute(
                viewUserConfig.createViewUserManuallyByUserUseCase(),
                CreateViewUserManuallyByUserUseCase.InputValues.of(
                        solUserEntity.getId(),
                        view),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(mapper.map(viewUserEntity));
    }
}
