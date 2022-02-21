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
    protected String taskId;
    protected BackgroundTaskForViewEntity.Status status;
    protected BackgroundTaskForViewEntity.Type type = BackgroundTaskForViewEntity.Type.TASK;
    protected String log;
    protected String userViewId;
}
