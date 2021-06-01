package com.sol.infrastructure.database.mongo.space.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.space.entity.SpaceEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class SpaceDoc extends SpaceEntity {
}
