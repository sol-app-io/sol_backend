package com.sol.client.task.v1;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.sol.client.task.v1.mappers.TaskResponseMapper;
import com.sol.client.task.v1.request.CreateTaskRequest;
import com.sol.client.task.v1.response.TaskResponse;
import com.sol.domain.task.config.TaskConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("taskControllerV1")
public class TaskController implements TaskResource {
    private final UseCaseExecutor useCaseExecutor;
    private final TaskConfig taskConfig;


    @Override
    public SuccessApiResponse<TaskResponse> create(CreateTaskRequest request, CredentialPrincipal credentialPrincipal) {
        return useCaseExecutor.execute(
                taskConfig.createTaskUseCase(),
                request.toInputValues(credentialPrincipal.getId()),
                output -> SuccessApiResponse.of(TaskResponseMapper.map(output.getEntity()))
        );
    }

    @Override
    public SuccessApiResponse<TaskResponse> singleton(String id, CredentialPrincipal credentialPrincipal) {
        return useCaseExecutor.execute(
                taskConfig.findTaskByIdUseCase(),
                IdInputValues.of(id),
                output ->SuccessApiResponse.of(TaskResponseMapper.map(output.getEntity().get()))
        );
    }

    //    @Override
//    public SuccessApiResponse<TaskResponse> update(String id, UpdateTaskRequest request, CredentialPrincipal credentialPrincipal) {
//        return useCaseExecutor.execute(
//                taskConfig.updateTaskUseCase(),
//                request.toInputValues(id),
//                output -> SuccessApiResponse.of(TaskResponseMapper.map(output.getEntity()))
//        );
//    }

//    @Override
//    public OkApiResponse delete(String id, CredentialPrincipal credentialPrincipal) {
//        return useCaseExecutor.execute(
//                taskConfig.deleteTaskUseCase(),
//                IdInputValues.of(id),
//                o -> new OkApiResponse()
//        );
//    }

//    @Override
//    public SuccessApiResponse<SearchApiResponse<TaskResponse>> collection(SearchTaskRequest request, CredentialPrincipal credentialPrincipal) {
//        return useCaseExecutor.execute(
//                taskConfig.findTaskUseCase(),
//                FiltersInputValues.of(request.toFilters()),
//                (o) -> SuccessApiResponse.of(SearchApiResponse.withItemsAndCount(
//                        o.getResult().getItems().stream().map(TaskResponseMapper::map).collect(Collectors.toList()),
//                        o.getResult().getCount()
//                ))
//        );
//    }

//    @Override
//    public SuccessApiResponse<TaskResponse> singleton(String id, CredentialPrincipal credentialPrincipal) {
//        return useCaseExecutor.execute(
//                taskConfig.findTaskByIdUseCase(),
//                IdInputValues.of(id),
//                o -> o.getEntity().map(entity -> SuccessApiResponse.of(TaskResponseMapper.map(entity)))
//                        .orElseThrow(TaskNotFoundException::new)
//        );
//    }

}
