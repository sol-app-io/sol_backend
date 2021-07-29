package com.sol.infrastructure.database.mongo.slot.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.slot.mapper.SlotMapper;
import com.sol.infrastructure.database.mongo.slot.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.slot.documents.SlotDoc;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.port.SlotRepository;
import com.sol.domain.slot.port.filters.SlotFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoSlotRepository implements SlotRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(SlotDoc.class);

    private final MongoTemplate mongoTemplate;
    private final SlotMapper mapper;

    @Override
    public SlotEntity save(SlotEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<SlotEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, SlotDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<SlotEntity> find(SlotFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, SlotDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), SlotDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), SlotDoc.class);
    }
}