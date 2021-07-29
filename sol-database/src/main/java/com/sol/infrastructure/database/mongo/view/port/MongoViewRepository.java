package com.sol.infrastructure.database.mongo.view.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.view.mapper.ViewMapper;
import com.sol.infrastructure.database.mongo.view.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.view.documents.ViewDoc;
import com.sol.domain.view.entity.ViewEntity;
import com.sol.domain.view.port.ViewRepository;
import com.sol.domain.view.port.filters.ViewFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoViewRepository implements ViewRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(ViewDoc.class);

    private final MongoTemplate mongoTemplate;
    private final ViewMapper mapper;

    @Override
    public ViewEntity save(ViewEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<ViewEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, ViewDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<ViewEntity> find(ViewFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ViewDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ViewDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), ViewDoc.class);
    }
}