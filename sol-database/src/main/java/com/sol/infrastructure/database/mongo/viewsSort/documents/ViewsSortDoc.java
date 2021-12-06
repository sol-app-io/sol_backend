package com.sol.infrastructure.database.mongo.viewsSort.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ViewsSortDoc extends BaseDocument {
    /**
     * Owner 
     */
    protected String ownerId;
    /**
     * Type 
     */
    protected ViewsSortEntity.Type type;
    /**
     * views 
     */
    protected List<String> viewsId;

 
}
