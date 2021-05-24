package com.sol.infrastructure.database.mongo.userOnPlan.documents;

import com.sol.domain.userOnPlan.entity.UserOnPlanEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class UserOnPlanDoc extends UserOnPlanEntity {

 
}
