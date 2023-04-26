package com.sol.client.viewUser.v1;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.viewUser.v1.request.*;
import com.sol.client.viewUser.v1.response.TaskInViewResponse;
import com.sol.client.viewUser.v1.response.ViewUserResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface ViewUserResource {

    @GetMapping(value =  ViewUserRoutes.MY_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<ViewUserResponse>> myRoot(@CurrentCredential CredentialPrincipal credentialPrincipal);

    @GetMapping(value =  ViewUserRoutes.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<ViewUserResponse>> all( @CurrentCredential CredentialPrincipal credentialPrincipal);

    @GetMapping(value =  ViewUserRoutes.FIND_VIEW_BY_TASK, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<TaskInViewResponse>> findByTask(@RequestParam String taskId,  @CurrentCredential CredentialPrincipal credentialPrincipal);

    @GetMapping(value =  ViewUserRoutes.FIND_TASKS_BY_VIEW, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<List<TaskInViewResponse>> findTasksByView(@RequestParam String viewId, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value =  ViewUserRoutes.ADD_TASK_TO_VIEW, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskInViewResponse> addTaskToView(@RequestBody CreateTaskInViewRequest request,  @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value =  ViewUserRoutes.SHOW, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<Boolean> show(@PathVariable String id,  @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value =  ViewUserRoutes.HIDE, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<Boolean> hide(@PathVariable String id,  @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value =  ViewUserRoutes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewUserResponse> create(@RequestBody CreateViewUserRequest request, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @DeleteMapping(value =  ViewUserRoutes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<String> delete(@RequestParam String taskId, @RequestParam String viewId, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PatchMapping(value = ViewUserRoutes.SINGLETON)
    SuccessApiResponse<ViewUserResponse> updateView(@PathVariable String id, @RequestBody UpdateUserViewRequest request, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PatchMapping(value = ViewUserRoutes.UPDATE_FULL)
    SuccessApiResponse<ViewUserResponse> updateAllView(
            @PathVariable String id,
            @RequestBody UpdateUserViewFullRequest request,
             @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value = ViewUserRoutes.PARAM_ADD)
    SuccessApiResponse<ViewUserResponse> paramAdd(@PathVariable String id, @RequestBody ViewParamRequest request, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value = ViewUserRoutes.PARAM_EDIT)
    SuccessApiResponse<ViewUserResponse> paramEdit(@PathVariable String id,@PathVariable String paramId, @RequestBody ViewParamRequest request,  @CurrentCredential CredentialPrincipal credentialPrincipal);

    @DeleteMapping(value = ViewUserRoutes.PARAM_DELETE)
    SuccessApiResponse<ViewUserResponse> paramDelete(@PathVariable String id, @PathVariable String paramId, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @PostMapping(value = ViewUserRoutes.CHANGE_SORT)
    SuccessApiResponse<Boolean> changeSort(@RequestBody ChangeViewSortRequest request, @CurrentCredential CredentialPrincipal credentialPrincipal);

    @DeleteMapping(value = ViewUserRoutes.SINGLETON)
    SuccessApiResponse<Boolean> delete(@PathVariable String id, @CurrentCredential CredentialPrincipal credentialPrincipal);
}
