package com.sol.infrastructure.database.mongo.space.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.space.mapper.SpaceMapper;
import com.sol.infrastructure.database.mongo.space.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.space.documents.SpaceDoc;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.port.SpaceRepository;
import com.sol.domain.space.port.filters.SpaceFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoSpaceRepository implements SpaceRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(SpaceDoc.class);

    private final MongoTemplate mongoTemplate;
    private final SpaceMapper mapper;

    @Override
    public SpaceEntity save(SpaceEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<SpaceEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, SpaceDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<SpaceEntity> find(SpaceFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, SpaceDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), SpaceDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), SpaceDoc.class);
    }
}