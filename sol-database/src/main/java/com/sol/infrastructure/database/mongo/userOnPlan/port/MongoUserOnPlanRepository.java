package com.sol.infrastructure.database.mongo.userOnPlan.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.userOnPlan.mapper.UserOnPlanMapper;
import com.sol.infrastructure.database.mongo.userOnPlan.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.userOnPlan.documents.UserOnPlanDoc;
import com.sol.domain.userOnPlan.entity.UserOnPlanEntity;
import com.sol.domain.userOnPlan.port.UserOnPlanRepository;
import com.sol.domain.userOnPlan.port.filters.UserOnPlanFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoUserOnPlanRepository implements UserOnPlanRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(UserOnPlanDoc.class);

    private final MongoTemplate mongoTemplate;
    private final UserOnPlanMapper mapper;

    @Override
    public UserOnPlanEntity save(UserOnPlanEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<UserOnPlanEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, UserOnPlanDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<UserOnPlanEntity> find(UserOnPlanFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, UserOnPlanDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), UserOnPlanDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), UserOnPlanDoc.class);
    }

    @Override
    public boolean exist(String s) {
        throw new RuntimeException();
    }
}