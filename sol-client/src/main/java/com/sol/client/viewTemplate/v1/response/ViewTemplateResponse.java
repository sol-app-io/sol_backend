package com.sol.client.viewTemplate.v1.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class ViewTemplateResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ParamResponse extends View.Params{
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        protected LocalDateTime valueDate;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        protected LocalDateTime valueDateIso;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ViewResponse extends View{
        protected ParamResponse param;
    }

    protected String id;
    protected String ownerId;
    protected String title;
    protected String description;
    protected String createdFromViewId;
    protected ViewTemplateEntity.Status status;
    protected ViewTemplateEntity.OwnerType ownerType;
    protected ViewTemplateEntity.Language language;
    protected ViewResponse view;
    protected Boolean addByDefault;
    protected Boolean canEdit;

}
