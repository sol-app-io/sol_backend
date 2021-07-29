package com.sol.infrastructure.database.mongo.userOnPlan.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.userOnPlan.entity.UserOnPlanEntity;
import com.sol.domain.userOnPlan.port.UserOnPlanIdGenerator;
import com.sol.infrastructure.database.mongo.userOnPlan.documents.UserOnPlanDoc;

@Component
@RequiredArgsConstructor
public class UserOnPlanMapper implements ExampleDataMapper<UserOnPlanEntity, UserOnPlanDoc> {
    private final UserOnPlanIdGenerator<ObjectId> idGenerator;

    @Override
    public UserOnPlanDoc map(UserOnPlanEntity entity) {
        return (UserOnPlanDoc) entity;
    }

    @Override
    public UserOnPlanEntity inverseMap(UserOnPlanDoc doc) {
        return doc;
    }
}
