package com.sol.infrastructure.database.mongo.repeatTaskConf.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.repeatTaskConf.mapper.RepeatTaskConfMapper;
import com.sol.infrastructure.database.mongo.repeatTaskConf.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.repeatTaskConf.documents.RepeatTaskConfDoc;
import com.sol.domain.repeatTaskConf.entity.RepeatTaskConfEntity;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfRepository;
import com.sol.domain.repeatTaskConf.port.filters.RepeatTaskConfFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoRepeatTaskConfRepository implements RepeatTaskConfRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(RepeatTaskConfDoc.class);

    private final MongoTemplate mongoTemplate;
    private final RepeatTaskConfMapper mapper;

    @Override
    public RepeatTaskConfEntity save(RepeatTaskConfEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<RepeatTaskConfEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, RepeatTaskConfDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<RepeatTaskConfEntity> find(RepeatTaskConfFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, RepeatTaskConfDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), RepeatTaskConfDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), RepeatTaskConfDoc.class);
    }
}