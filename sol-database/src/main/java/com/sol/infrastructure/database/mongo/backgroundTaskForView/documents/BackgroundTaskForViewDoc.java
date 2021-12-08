package com.sol.infrastructure.database.mongo.backgroundTaskForView.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.backgroundTaskForView.entity.BackgroundTaskForViewEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BackgroundTaskForViewDoc extends BaseDocument {
    /**
     * task 
     */
    protected String taskId;
    /**
     * status 
     */
    protected BackgroundTaskForViewEntity.Status status;
    /**
     * Log 
     */
    protected String log;

 
}
