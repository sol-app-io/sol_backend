package com.sol.infrastructure.database.mongo.slot.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.base.entity.ExternalId;
import com.sol.domain.slot.entity.SlotEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class SlotDoc extends BaseDocument {

    /**
     * title
     */
    protected String title;
    /**
     * owner
     */
    protected ObjectId ownerId;
    /**
     * createdFromTaskId
     */
    protected ObjectId createdFromTaskId;
    /**
     * spaceId
     */
    protected ObjectId spaceId;
    /**
     * viewIds
     */
    protected List<ObjectId> viewIds;
    /**
     * start Time
     */
    protected LocalDateTime startTime;
    /**
     * End Time
     */
    protected LocalDateTime endTime;
    /**
     * slotsMilliseconds;
     */
    protected Long slotsMilliseconds = 0l;
    /**
     * External IDs
     */
    protected List<ExternalId> externalIds = new ArrayList<>();
    protected Integer timezone = 0;


}
