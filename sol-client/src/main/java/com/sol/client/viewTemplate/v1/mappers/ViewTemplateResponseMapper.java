package com.sol.client.viewTemplate.v1.mappers;

import com.sol.client.viewTemplate.v1.response.ViewTemplateResponse;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewTemplateResponseMapper {
    public static ViewTemplateResponse.ParamResponse map(View.Params entity) {
        ViewTemplateResponse.ParamResponse param = new ViewTemplateResponse.ParamResponse();
        param.setId(entity.getId());
        param.setType(entity.getType());
        param.setValueBool(entity.getValueBool());
        param.setValueString(entity.getValueString());
        param.setValueDate(entity.getValueDate());
        param.setValueDateIso(entity.getValueDate());
        return param;
    }

    public static ViewTemplateResponse.ViewResponse map(View entity) {
        ViewTemplateResponse.ViewResponse view = new ViewTemplateResponse.ViewResponse();
        view.setIcon(entity.getIcon());
        view.setTitle(entity.getTitle());
        view.setDescription(entity.getDescription());
        view.setAddedType(entity.getAddedType());
        view.setDisplayMode(entity.getDisplayMode());
        view.setViewType(entity.getViewType());
        view.setSortType(entity.getSortType());
        view.setParams(entity.getParams() != null ? entity.getParams().stream().map(ViewTemplateResponseMapper::map).collect(Collectors.toList()) : new ArrayList<>());
        return view;
    }

    public static ViewTemplateResponse map(ViewTemplateEntity entity) {
        return ViewTemplateResponse.builder()
                .id(entity.getId())
                .ownerId(entity.getOwnerId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .createdFromViewId(entity.getCreatedFromViewId())
                .status(entity.getStatus())
                .ownerType(entity.getOwnerType())
                .language(entity.getLanguage())
                .view(map(entity.getView()))
                .addByDefault(entity.getAddByDefault())
                .canEdit(entity.getCanEdit())
                .build();
    }

}
