package com.sol.infrastructure.database.mongo.viewUser.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.view.entity.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ViewUserDoc extends BaseDocument {
    /**
     * Owner 
     */
    protected String ownerId;
    /**
     * Created from template 
     */
    protected String createdFromTemplateId;
    /**
     * Has new Task to Add 
     */
    protected Boolean hasNewTaskToAdd;
    /**
     * Has Task Added 
     */
    protected Boolean hasTaskAdded;
    /**
     * Can Edit 
     */
    protected Boolean canEdit;
    /**
     * view 
     */
    protected View view;

 
}
