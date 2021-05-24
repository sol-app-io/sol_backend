package com.sol.infrastructure.database.mongo.repeatTaskConf.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.repeatTaskConf.entity.RepeatTaskConfEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class RepeatTaskConfDoc extends RepeatTaskConfEntity {

 
}
