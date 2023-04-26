package com.sol.infrastructure.database.mongo.reminder.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.reminder.mapper.ReminderMapper;
import com.sol.infrastructure.database.mongo.reminder.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.reminder.documents.ReminderDoc;
import com.sol.domain.reminder.entity.ReminderEntity;
import com.sol.domain.reminder.port.ReminderRepository;
import com.sol.domain.reminder.port.filters.ReminderFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoReminderRepository implements ReminderRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(ReminderDoc.class);

    private final MongoTemplate mongoTemplate;
    private final ReminderMapper mapper;

    @Override
    public ReminderEntity save(ReminderEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<ReminderEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, ReminderDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<ReminderEntity> find(ReminderFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ReminderDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ReminderDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), ReminderDoc.class);
    }

    @Override
    public boolean exist(String s) {
        throw new RuntimeException();
    }
}