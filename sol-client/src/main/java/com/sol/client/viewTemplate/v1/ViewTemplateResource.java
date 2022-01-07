package com.sol.client.viewTemplate.v1;

import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.sol.client.viewTemplate.v1.request.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.sol.client.viewTemplate.v1.response.ViewTemplateResponse;

@Api(tags = "View Template API", description = "View Template docs")
@RestController
public interface ViewTemplateResource {

    @ApiOperation("Создание нового документа в ресурсе")
    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> create(@RequestBody CreateViewTemplateRequest request);

    @ApiOperation("Обновление документа в ресурсе")
    @PutMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> update(@PathVariable("id") String id, @RequestBody UpdateViewTemplateRequest request);

    @ApiOperation("Удаление документа в ресурсе")
    @DeleteMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    OkApiResponse delete(@PathVariable("id") String id);

    @ApiOperation("Добавление add param")
    @PostMapping(value = Routes.ADD_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> addParam(@PathVariable("id") String id, @RequestBody ViewTemplateAddParamRequest request);

    @ApiOperation("Редактирование param")
    @PostMapping(value = Routes.EDIT_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> editParam(@PathVariable("id") String id, @RequestBody ViewTemplateEditParamRequest request);

    @ApiOperation("Удалить param")
    @PostMapping(value = Routes.DELETE_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> deleteParam(@PathVariable("id") String id, @RequestBody ViewTemplateDeleteParamRequest request);

    @ApiOperation("Получение документа в ресурсе")
    @GetMapping(value =  Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SearchApiResponse<ViewTemplateResponse>> collection(SearchViewTemplateRequest request);

    @ApiOperation("Получение продукта по ID")
    @GetMapping(value =  Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse>  singleton(@PathVariable("id") String id);

    @ApiOperation("Назначить всем, если админ")
    @PostMapping(value =  Routes.BULK_ADD, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<String>  makeByDefault(@PathVariable("id") String id);
}
