package com.sol.infrastructure.database.mongo.plan.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.plan.mapper.PlanMapper;
import com.sol.infrastructure.database.mongo.plan.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.plan.documents.PlanDoc;
import com.sol.domain.plan.entity.PlanEntity;
import com.sol.domain.plan.port.PlanRepository;
import com.sol.domain.plan.port.filters.PlanFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoPlanRepository implements PlanRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(PlanDoc.class);

    private final MongoTemplate mongoTemplate;
    private final PlanMapper mapper;

    @Override
    public PlanEntity save(PlanEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<PlanEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, PlanDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<PlanEntity> find(PlanFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, PlanDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), PlanDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), PlanDoc.class);
    }
}