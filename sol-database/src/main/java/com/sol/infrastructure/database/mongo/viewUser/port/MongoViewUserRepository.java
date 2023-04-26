package com.sol.infrastructure.database.mongo.viewUser.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.filters.ViewUserByTemplateFilters;
import com.sol.infrastructure.database.mongo.viewUser.query.ViewUserByTemplateQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.viewUser.mapper.ViewUserMapper;
import com.sol.infrastructure.database.mongo.viewUser.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.viewUser.documents.ViewUserDoc;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewUser.port.filters.ViewUserFilters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoViewUserRepository implements ViewUserRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(ViewUserDoc.class);

    private final MongoTemplate mongoTemplate;
    private final ViewUserMapper mapper;

    @Override
    public ViewUserEntity save(ViewUserEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<ViewUserEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, ViewUserDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<ViewUserEntity> find(ViewUserFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ViewUserDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ViewUserDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), ViewUserDoc.class);
    }

    @Override
    public SearchResult<ViewUserEntity> find(ViewUserByTemplateFilters filters) {
        Query query = new ViewUserByTemplateQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ViewUserDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ViewUserDoc.class)
        );
    }

    @Override
    public List<ViewUserEntity> find(String solUserId) {
        Criteria criteria = Criteria.where("ownerId").is(solUserId);
        return mongoTemplate.find(new Query(criteria), ViewUserDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList());
    }

    @Override
    public Optional<ViewUserEntity> findByTemplateAndUser(String solUserId, String templateId) {
        Criteria criteria = Criteria.where("ownerId").is(solUserId).and("createdFromTemplateId").is(templateId);
        return Optional.ofNullable(mongoTemplate.findOne(new Query(criteria), ViewUserDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public boolean exist(String s) {
        throw new RuntimeException();
    }
}