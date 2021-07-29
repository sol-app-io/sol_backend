package com.sol.infrastructure.database.mongo.plan.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.plan.entity.PlanEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class PlanDoc extends PlanEntity {

 
}
