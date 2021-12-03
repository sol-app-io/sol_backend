package com.sol.infrastructure.database.mongo.category.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryDoc extends BaseDocument {
    /**
     * Title 
     */
    protected String title;
    /**
     * description 
     */
    protected String description;
    /**
     * Parent Category 
     */
    protected ObjectId parentId;

 
}
