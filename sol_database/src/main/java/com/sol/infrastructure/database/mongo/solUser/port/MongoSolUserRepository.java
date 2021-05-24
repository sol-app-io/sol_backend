package com.sol.infrastructure.database.mongo.solUser.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.solUser.mapper.SolUserMapper;
import com.sol.infrastructure.database.mongo.solUser.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.solUser.documents.SolUserDoc;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.solUser.port.filters.SolUserFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoSolUserRepository implements SolUserRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(SolUserDoc.class);

    private final MongoTemplate mongoTemplate;
    private final SolUserMapper mapper;

    @Override
    public SolUserEntity save(SolUserEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<SolUserEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, SolUserDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<SolUserEntity> find(SolUserFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, SolUserDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), SolUserDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), SolUserDoc.class);
    }
}