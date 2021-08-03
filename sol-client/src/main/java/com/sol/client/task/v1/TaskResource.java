package com.sol.client.task.v1;

import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.sol.client.task.v1.request.CreateTaskRequest;
import com.sol.client.task.v1.request.UpdateTaskRequest;
import com.sol.client.task.v1.request.SearchTaskRequest;
import com.sol.client.task.v1.response.TaskResponse;
import springfox.documentation.annotations.ApiIgnore;

//@Api(tags = "Task API", description = "Task docs")
@RestController
public interface TaskResource {

    @ApiOperation("Создание нового документа в ресурсе")
    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskResponse> create(@RequestBody CreateTaskRequest request,@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

//    @ApiOperation("Обновление документа в ресурсе")
//    @PutMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
//    SuccessApiResponse<TaskResponse> update(@PathVariable("id") String id, @RequestBody UpdateTaskRequest request,@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

//    @ApiOperation("Удаление документа в ресурсе")
//    @DeleteMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
//    OkApiResponse delete(@PathVariable("id") String id,@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

//    @ApiOperation("Получение документа в ресурсе")
//    @GetMapping(value =  Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
//    SuccessApiResponse<SearchApiResponse<TaskResponse>> collection(SearchTaskRequest request,@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);

    @ApiOperation("Получение продукта по ID")
    @GetMapping(value =  Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<TaskResponse>  singleton(@PathVariable("id") String id,@ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal);
}
