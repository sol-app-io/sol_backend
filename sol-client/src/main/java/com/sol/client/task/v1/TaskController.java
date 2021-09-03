package com.sol.client.task.v1;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.task.v1.mappers.TaskResponseMapper;
import com.sol.client.task.v1.request.ChangeSortOfTasksRequest;
import com.sol.client.task.v1.request.CreateTaskRequest;
import com.sol.client.task.v1.request.TaskEditTitleRequest;
import com.sol.client.task.v1.response.TaskResponse;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.task.config.TaskConfig;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.usecases.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("taskControllerV1")
public class TaskController implements TaskResource {
    private final UseCaseExecutor useCaseExecutor;
    private final TaskConfig taskConfig;
    private final SolUserConfig solUserConfig;


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
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()).getEntity();

        TaskResponse taskResponse = useCaseExecutor.execute(
                taskConfig.findTaskByIdUseCase(),
                IdInputValues.of(id),
                output -> TaskResponseMapper.map(output.getEntity().get())
        );

        List<TaskResponse> taskEntities = useCaseExecutor.execute(
                taskConfig.findTaskByParentUseCase(),
                FindTaskByParentUseCase.InputValues.of(taskResponse.getId(), solUserEntity.getId()),
                output -> output.getEntity().stream().map(TaskResponseMapper::map).collect(Collectors.toList())
        );

        taskResponse.setChild(taskEntities);
        return SuccessApiResponse.of(taskResponse);
    }

    @Override
    public SuccessApiResponse<TaskResponse> done(String id, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()).getEntity();

        TaskResponse taskResponse = useCaseExecutor.execute(
                taskConfig.makeTaskDoneUseCase(),
                MakeTaskDoneUseCase.InputValues.of(id, solUserEntity.getId()),
                output -> TaskResponseMapper.map(output.getEntity())
        );

        return SuccessApiResponse.of(taskResponse);
    }

    @Override
    public SuccessApiResponse<TaskResponse> open(String id, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()).getEntity();

        TaskResponse taskResponse = useCaseExecutor.execute(
                taskConfig.makeTaskOpenUseCase(),
                MakeTaskOpenUseCase.InputValues.of(id, solUserEntity.getId()),
                output -> TaskResponseMapper.map(output.getEntity())
        );

        return SuccessApiResponse.of(taskResponse);
    }

    @Override
    public SuccessApiResponse<TaskResponse> editHeader(String id, TaskEditTitleRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(
                MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()
        ).getEntity();

        TaskResponse taskResponse = useCaseExecutor.execute(
                taskConfig.editTitleIconTaskUseCase(),
                EditTitleIconTaskUseCase.InputValues.of(id, request.getTitle(), request.getIcon(), solUserEntity.getId()),
                output -> TaskResponseMapper.map(output.getEntity())
        );

        return SuccessApiResponse.of(taskResponse);
    }

    @Override
    public SuccessApiResponse<List<TaskResponse>> sortNum(ChangeSortOfTasksRequest request, CredentialPrincipal credentialPrincipal){
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(
                MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()
        ).getEntity();

        List<TaskResponse> tasks = useCaseExecutor.execute(
                taskConfig.changeSortTasksUseCase(),
                ChangeSortTasksUseCase.InputValues.of(request.getTasks(), solUserEntity.getId()),
                output -> output.getEntity().stream().map(TaskResponseMapper::map).collect(Collectors.toList())
        );

        return SuccessApiResponse.of(tasks);
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
