package com.sol.infrastructure.database.mongo.solUser.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.solUser.entity.SolUserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class SolUserDoc extends SolUserEntity {

 
}
