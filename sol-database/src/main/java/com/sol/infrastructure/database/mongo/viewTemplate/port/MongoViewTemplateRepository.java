package com.sol.infrastructure.database.mongo.viewTemplate.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateAdminsFilters;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateDefaultsFilters;
import com.sol.infrastructure.database.mongo.viewTemplate.query.ViewTemplateAdminsQuery;
import com.sol.infrastructure.database.mongo.viewTemplate.query.ViewTemplateDefaultsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.viewTemplate.mapper.ViewTemplateMapper;
import com.sol.infrastructure.database.mongo.viewTemplate.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.viewTemplate.documents.ViewTemplateDoc;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoViewTemplateRepository implements ViewTemplateRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(ViewTemplateDoc.class);

    private final MongoTemplate mongoTemplate;
    private final ViewTemplateMapper mapper;

    @Override
    public ViewTemplateEntity save(ViewTemplateEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<ViewTemplateEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, ViewTemplateDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<ViewTemplateEntity> find(ViewTemplateFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ViewTemplateDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ViewTemplateDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), ViewTemplateDoc.class);
    }

    @Override
    public SearchResult<ViewTemplateEntity> find(ViewTemplateAdminsFilters filters) {
        Query query = new ViewTemplateAdminsQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ViewTemplateDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ViewTemplateDoc.class)
        );
    }

    @Override
    public SearchResult<ViewTemplateEntity> find(ViewTemplateDefaultsFilters filters) {
        Query query = new ViewTemplateDefaultsQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ViewTemplateDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ViewTemplateDoc.class)
        );
    }

    @Override
    public boolean exist(String s) {
        throw new RuntimeException();
    }
}