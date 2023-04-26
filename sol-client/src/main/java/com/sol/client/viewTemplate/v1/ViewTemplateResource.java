package com.sol.client.viewTemplate.v1;

import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.sol.client.viewTemplate.v1.request.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.sol.client.viewTemplate.v1.response.ViewTemplateResponse;

@RestController
public interface ViewTemplateResource {

    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> create(@RequestBody CreateViewTemplateRequest request);

    @PutMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> update(@PathVariable("id") String id, @RequestBody UpdateViewTemplateRequest request);

    @DeleteMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    OkApiResponse delete(@PathVariable("id") String id);

    @PostMapping(value = Routes.ADD_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> addParam(@PathVariable("id") String id, @RequestBody ViewTemplateAddParamRequest request);

    @PostMapping(value = Routes.EDIT_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> editParam(@PathVariable("id") String id, @RequestBody ViewTemplateEditParamRequest request);

    @PostMapping(value = Routes.DELETE_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse> deleteParam(@PathVariable("id") String id, @RequestBody ViewTemplateDeleteParamRequest request);

    @GetMapping(value =  Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SearchApiResponse<ViewTemplateResponse>> collection(SearchViewTemplateRequest request);

    @GetMapping(value =  Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<ViewTemplateResponse>  singleton(@PathVariable("id") String id);

    @PostMapping(value =  Routes.BULK_ADD, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<String>  makeByDefault(@PathVariable("id") String id);
}
