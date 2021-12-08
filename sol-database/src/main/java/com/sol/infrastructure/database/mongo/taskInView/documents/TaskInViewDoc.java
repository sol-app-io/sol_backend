package com.sol.infrastructure.database.mongo.taskInView.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TaskInViewDoc extends BaseDocument {
    /**
     * View 
     */
    protected String viewId;
    /**
     * Task 
     */
    protected String taskId;
    /**
     * sort  
     */
    protected Integer sortNum;

 
}
