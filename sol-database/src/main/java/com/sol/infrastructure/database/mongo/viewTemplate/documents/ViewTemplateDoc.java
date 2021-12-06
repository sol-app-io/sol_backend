package com.sol.infrastructure.database.mongo.viewTemplate.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ViewTemplateDoc extends BaseDocument {
    /**
     * Owner 
     */
    protected String ownerId;
    /**
     * Заголовок 
     */
    protected String title;
    /**
     * description 
     */
    protected String description;
    /**
     * created From View 
     */
    protected String createdFromViewId;
    /**
     * Status 
     */
    protected ViewTemplateEntity.Status status;
    /**
     * owner Type 
     */
    protected ViewTemplateEntity.OwnerType ownerType;
    /**
     * language 
     */
    protected ViewTemplateEntity.Language language;
    /**
     * view 
     */
    protected View view;
    /**
     * add By Default 
     */
    protected Boolean addByDefault;
    /**
     * can Edit 
     */
    protected Boolean canEdit;

 
}
