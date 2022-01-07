package com.sol.client.viewTemplate.v1.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@ApiModel("View Template: общая модель ответа")
public class ViewTemplateResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @ApiModel("View Param")
    public static class ParamResponse extends View.Params{
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        protected LocalDateTime valueDate;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        protected LocalDateTime valueDateIso;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ApiModel("View Param")
    public static class ViewResponse extends View{
        protected ParamResponse param;
    }

    @ApiModelProperty("Идентификатор")
    protected String id;
    @ApiModelProperty("ownerId")
    protected String ownerId;
    @ApiModelProperty("title")
    protected String title;
    @ApiModelProperty("description")
    protected String description;
    @ApiModelProperty("createdFromViewId")
    protected String createdFromViewId;
    @ApiModelProperty("status")
    protected ViewTemplateEntity.Status status;
    @ApiModelProperty("ownerType")
    protected ViewTemplateEntity.OwnerType ownerType;
    @ApiModelProperty("language")
    protected ViewTemplateEntity.Language language;
    @ApiModelProperty("view")
    protected ViewResponse view;
    @ApiModelProperty("addByDefault")
    protected Boolean addByDefault;
    @ApiModelProperty("canEdit")
    protected Boolean canEdit;

}
