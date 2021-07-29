package com.sol.infrastructure.database.mongo.view.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.view.entity.ViewEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class ViewDoc extends ViewEntity {

 
}
