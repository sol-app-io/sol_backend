package com.sol.infrastructure.database.mongo.view.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.view.entity.ViewEntity;
import com.sol.domain.view.port.ViewIdGenerator;
import com.sol.infrastructure.database.mongo.view.documents.ViewDoc;

@Component
@RequiredArgsConstructor
public class ViewMapper implements ExampleDataMapper<ViewEntity, ViewDoc> {
    private final ViewIdGenerator<ObjectId> idGenerator;

    @Override
    public ViewDoc map(ViewEntity entity) {
        return (ViewDoc) entity;
    }

    @Override
    public ViewEntity inverseMap(ViewDoc doc) {
        return doc;
    }
}
