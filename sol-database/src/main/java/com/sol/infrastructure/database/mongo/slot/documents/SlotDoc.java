package com.sol.infrastructure.database.mongo.slot.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.slot.entity.SlotEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class SlotDoc extends SlotEntity {

 
}
