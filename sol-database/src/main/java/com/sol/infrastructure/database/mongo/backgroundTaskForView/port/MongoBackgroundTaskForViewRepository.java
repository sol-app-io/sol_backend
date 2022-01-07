package com.sol.infrastructure.database.mongo.backgroundTaskForView.port;

import com.mongodb.client.result.UpdateResult;
import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.backgroundTaskForView.mapper.BackgroundTaskForViewMapper;
import com.sol.infrastructure.database.mongo.backgroundTaskForView.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.backgroundTaskForView.documents.BackgroundTaskForViewDoc;
import com.sol.domain.backgroundTaskForView.entity.BackgroundTaskForViewEntity;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;
import com.sol.domain.backgroundTaskForView.port.filters.BackgroundTaskForViewFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoBackgroundTaskForViewRepository implements BackgroundTaskForViewRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(BackgroundTaskForViewDoc.class);

    private final MongoTemplate mongoTemplate;
    private final BackgroundTaskForViewMapper mapper;

    @Override
    public BackgroundTaskForViewEntity save(BackgroundTaskForViewEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<BackgroundTaskForViewEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, BackgroundTaskForViewDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<BackgroundTaskForViewEntity> find(BackgroundTaskForViewFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, BackgroundTaskForViewDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), BackgroundTaskForViewDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), BackgroundTaskForViewDoc.class);
    }



    @Override
    public Optional<BackgroundTaskForViewEntity> findNext() {
        Criteria criteria = Criteria.where("status").is(BackgroundTaskForViewEntity.Status.NEW);
        Query query = new Query(criteria);
        query = query.limit(1);

        Update update = Update
                .update("status", BackgroundTaskForViewEntity.Status.IN_PROCESS);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, BackgroundTaskForViewDoc.class);

        if (updateResult.getModifiedCount() == 0) {
            return Optional.empty();
        }

        ObjectId id = updateResult.getUpsertedId().asObjectId().getValue();
        return Optional.ofNullable(mongoTemplate.findById(id, BackgroundTaskForViewDoc.class)).map(mapper::inverseMap);
    }
}