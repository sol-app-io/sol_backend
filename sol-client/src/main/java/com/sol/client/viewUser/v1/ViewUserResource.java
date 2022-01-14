package com.sol.client.viewUser.v1;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.viewUser.v1.request.ChangeViewSortRequest;
import com.sol.client.viewUser.v1.request.CreateTaskInViewRequest;
import com.sol.client.viewUser.v1.request.ViewParamRequest;
import com.sol.client.viewUser.v1.request.UpdateUserViewRequest;
import com.sol.client.viewUser.v1.response.TaskInViewResponse;
import com.sol.client.viewUser.v1.response.ViewUserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "View User API", description = "View ViewUserController docs")
@RestController
public interface ViewUserResource {

    @ApiOperation("Получение документа в ресурсе")
    @GetMapping(value =  ViewUserRoutes.MY_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<ViewUserResponse>> myRoot(@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("Получение view, в которых есть таск")
    @GetMapping(value =  ViewUserRoutes.FIND_VIEW_BY_TASK, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<TaskInViewResponse>> findByTask(@RequestParam String taskId, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("Получение тасков, в которые есть во вью")
    @GetMapping(value =  ViewUserRoutes.FIND_TASKS_BY_VIEW, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<TaskInViewResponse>> findTasksByView(@RequestParam String viewId, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("Добавить таск во вью")
    @PostMapping(value =  ViewUserRoutes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskInViewResponse> findByTask(@RequestBody CreateTaskInViewRequest request, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("Удалить таск во вью")
    @DeleteMapping(value =  ViewUserRoutes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<String> delete(@RequestParam String taskId, @RequestParam String viewId, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("Обновить view")
    @PatchMapping(value = ViewUserRoutes.SINGLETON)
    SuccessApiResponse<ViewUserResponse> updateView(@PathVariable String id, @RequestBody UpdateUserViewRequest request, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("view param add")
    @PostMapping(value = ViewUserRoutes.PARAM_ADD)
    SuccessApiResponse<ViewUserResponse> paramAdd(@PathVariable String id, @RequestBody ViewParamRequest request, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("view param edit")
    @PostMapping(value = ViewUserRoutes.PARAM_EDIT)
    SuccessApiResponse<ViewUserResponse> paramEdit(@PathVariable String id,@PathVariable String paramId, @RequestBody ViewParamRequest request, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("view param add")
    @DeleteMapping(value = ViewUserRoutes.PARAM_DELETE)
    SuccessApiResponse<ViewUserResponse> paramDelete(@PathVariable String id, @PathVariable String paramId, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("Change view sort params")
    @PostMapping(value = ViewUserRoutes.CHANGE_SORT)
    SuccessApiResponse<Boolean> changeSort(@PathVariable String id, @RequestBody ChangeViewSortRequest request, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("View delete")
    @DeleteMapping(value = ViewUserRoutes.SINGLETON)
    SuccessApiResponse<Boolean> delete(@PathVariable String id, @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);
}
