package com.sol.client.viewTemplate.v1.mappers;

import com.sol.client.viewTemplate.v1.response.ViewTemplateResponse;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;

public class ViewTemplateResponseMapper {
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
                .view(entity.getView())
                .addByDefault(entity.getAddByDefault())
                .canEdit(entity.getCanEdit())
                .build();
    }

}
