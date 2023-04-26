package com.sol.client.task.v1;

import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.task.v1.request.ChangeSortOfTasksRequest;
import com.sol.client.task.v1.request.CreateTaskRequest;
import com.sol.client.task.v1.request.TaskEditTitleRequest;
import com.sol.client.task.v1.response.TaskResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(tags = "Task API", description = "Task docs")
@RestController
public interface TaskResource {

    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskResponse> create(@RequestBody CreateTaskRequest request,  @CurrentCredential CredentialPrincipal credentialPrincipal);

//    @ApiOperation("Обновление документа в ресурсе")
//    @PutMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
//    SuccessApiResponse<TaskResponse> update(@PathVariable("id") String id, @RequestBody UpdateTaskRequest request,@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

//    @ApiOperation("Удаление документа в ресурсе")
//    @DeleteMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
//    OkApiResponse delete(@PathVariable("id") String id,@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

//    @ApiOperation("Получение документа в ресурсе")
//    @GetMapping(value =  Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
//    SuccessApiResponse<SearchApiResponse<TaskResponse>> collection(SearchTaskRequest request,@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @GetMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskResponse> singleton(@PathVariable("id") String id,  @CurrentCredential CredentialPrincipal credentialPrincipal);

    @GetMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<TaskResponse>> tasks(@CurrentCredential CredentialPrincipal credentialPrincipal);

    @GetMapping(value = Routes.SUGGEST_SEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SearchApiResponse<TaskResponse>> suggestSearch(@RequestParam String viewId, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @GetMapping(value = Routes.SUGGEST_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<Long> suggestCount(@RequestParam String viewId,  @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value = Routes.DONE, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskResponse> done(@PathVariable("id") String id,  @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value = Routes.OPEN, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskResponse> open(@PathVariable("id") String id, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PatchMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskResponse> editHeader(
            @PathVariable("id") String id,
            @RequestBody TaskEditTitleRequest request,
@CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value = Routes.SORT_NUM, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<TaskResponse>> sortNum(@RequestBody ChangeSortOfTasksRequest request, @CurrentCredential CredentialPrincipal credentialPrincipal);

}
