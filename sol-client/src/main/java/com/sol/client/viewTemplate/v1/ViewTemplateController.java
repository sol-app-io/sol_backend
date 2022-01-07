package com.sol.client.viewTemplate.v1;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.sol.client.viewTemplate.v1.mappers.ViewTemplateResponseMapper;
import com.sol.client.viewTemplate.v1.request.*;
import com.sol.client.viewTemplate.v1.response.ViewTemplateResponse;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.config.ViewTemplateConfig;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.usecases.*;
import com.sol.domain.viewUser.config.ViewUserConfig;
import com.sol.domain.viewUser.usecases.CreateViewUserFromTemplateBulkByAdminUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("viewTemplateControllerV1")
public class ViewTemplateController implements ViewTemplateResource {
    private final UseCaseExecutor useCaseExecutor;
    private final ViewTemplateConfig viewTemplateConfig;
    private final ViewUserConfig viewUserConfig;


    @Override
    public SuccessApiResponse<ViewTemplateResponse> create(CreateViewTemplateRequest request) {
        return useCaseExecutor.execute(
                viewTemplateConfig.createViewTemplateByAdminUseCase(),
                request.toInputValues(),
                output -> SuccessApiResponse.of(ViewTemplateResponseMapper.map(output.getEntity()))
        );
    }

    @Override
    public SuccessApiResponse<ViewTemplateResponse> update(String id, UpdateViewTemplateRequest request) {
        return useCaseExecutor.execute(
                viewTemplateConfig.updateViewInTemplateByAdminUseCase(),
                request.toInputValues(id),
                output -> SuccessApiResponse.of(ViewTemplateResponseMapper.map(output.getEntity()))
        );
    }

    @Override
    public OkApiResponse delete(String id) {
        return useCaseExecutor.execute(
                viewTemplateConfig.deleteViewTemplateUseCase(),
                DeleteViewTemplateUseCase.InputValues.of(id),
                o -> new OkApiResponse()
        );
    }

    @Override
    public SuccessApiResponse<SearchApiResponse<ViewTemplateResponse>> collection(SearchViewTemplateRequest request) {
        return useCaseExecutor.execute(
                viewTemplateConfig.findViewTemplatesUseCase(),
                FindViewTemplatesUseCase.InputValues.of(request.toFilters()),
                (o) -> SuccessApiResponse.of(SearchApiResponse.withItemsAndCount(
                        o.getResult().getItems().stream().map(ViewTemplateResponseMapper::map).collect(Collectors.toList()),
                        o.getResult().getCount()
                ))
        );
    }

    @Override
    public SuccessApiResponse<ViewTemplateResponse> singleton(String id) {
        return useCaseExecutor.execute(
                viewTemplateConfig.findViewTemplateByIdUseCase(),
                IdInputValues.of(id),
                o -> o.getEntity().map(entity -> SuccessApiResponse.of(ViewTemplateResponseMapper.map(entity)))
                        .orElseThrow(ViewTemplateNotFoundException::new)
        );
    }

    @Override
    public SuccessApiResponse<String> makeByDefault(String id) {
        ViewTemplateEntity viewTemplateEntity = useCaseExecutor.execute(
                viewTemplateConfig.findViewTemplateByIdUseCase(),
                IdInputValues.of(id),
                o -> o.getEntity().orElseThrow(ViewTemplateNotFoundException::new)
        );

        useCaseExecutor.execute(
                viewUserConfig.createViewUserFromTemplateBulkByAdminUseCase(),
                CreateViewUserFromTemplateBulkByAdminUseCase.InputValues.of(viewTemplateEntity)
        );

        return SuccessApiResponse.of("Ok");
    }

    @Override
    public SuccessApiResponse<ViewTemplateResponse> addParam(String id, ViewTemplateAddParamRequest request) {
        ViewTemplateEntity viewTemplateEntity = useCaseExecutor.execute(
                viewTemplateConfig.addParamsToViewInTemplateUseCase(),
                AddParamsToViewInTemplateUseCase.InputValues.of(
                        id,
                        request.getType(),
                        request.getValueString(),
                        request.getValueDate(),
                        request.getValueBool()),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(ViewTemplateResponseMapper.map(viewTemplateEntity));
    }

    @Override
    public SuccessApiResponse<ViewTemplateResponse> editParam(String id, ViewTemplateEditParamRequest request) {
        ViewTemplateEntity viewTemplateEntity = useCaseExecutor.execute(
                viewTemplateConfig.editParamsToViewInTemplateUseCase(),
                EditParamsToViewInTemplateUseCase.InputValues.of(
                        id,
                        request.getId(),
                        request.getType(),
                        request.getValueString(),
                        request.getValueDate(),
                        request.getValueBool()),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(ViewTemplateResponseMapper.map(viewTemplateEntity));
    }

    @Override
    public SuccessApiResponse<ViewTemplateResponse> deleteParam(String id, ViewTemplateDeleteParamRequest request) {
        ViewTemplateEntity viewTemplateEntity = useCaseExecutor.execute(
                viewTemplateConfig.deleteParamsToViewInTemplateUseCase(),
                DeleteParamsToViewInTemplateUseCase.InputValues.of(
                        id,
                        request.getId()
                ),
                o -> o.getEntity()
        );

        return SuccessApiResponse.of(ViewTemplateResponseMapper.map(viewTemplateEntity));
    }
}
