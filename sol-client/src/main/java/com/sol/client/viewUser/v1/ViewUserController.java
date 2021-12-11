package com.sol.client.viewUser.v1;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.sol.client.viewUser.v1.mappers.ViewUserResponseMapper;
import com.sol.client.viewUser.v1.request.CreateTaskInViewRequest;
import com.sol.client.viewUser.v1.response.TaskInViewResponse;
import com.sol.client.viewUser.v1.response.ViewUserResponse;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.taskInView.config.TaskInViewConfig;
import com.sol.domain.taskInView.usecases.CreateTaskInViewUseCase;
import com.sol.domain.taskInView.usecases.FindByTaskUseCase;
import com.sol.domain.viewTemplate.config.ViewTemplateConfig;
import com.sol.domain.viewUser.config.ViewUserConfig;
import com.sol.domain.viewUser.usecases.FindViewBySolUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("viewUserControllerV1")
public class ViewUserController implements ViewUserResource {
    private final UseCaseExecutor useCaseExecutor;
    private final ViewTemplateConfig viewTemplateConfig;
    private final ViewUserConfig viewUserConfig;
    private final TaskInViewConfig taskInViewConfig;
    private final SolUserConfig solUserConfig;
    private final ViewUserResponseMapper mapper = new ViewUserResponseMapper();


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
    public SuccessApiResponse<TaskInViewResponse> findByTask(CreateTaskInViewRequest request, CredentialPrincipal credentialPrincipal) {
        return SuccessApiResponse.of(
                useCaseExecutor.execute(
                        taskInViewConfig.createTaskInViewUseCase(),
                        CreateTaskInViewUseCase.InputValues.of(request.getViewId(), request.getTaskId(), request.getSortNum()),
                        o -> mapper.map(o.getEntity())
                )
        );
    }
}
