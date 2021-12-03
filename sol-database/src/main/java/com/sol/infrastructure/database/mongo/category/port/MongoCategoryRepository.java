package com.sol.infrastructure.database.mongo.category.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.category.mapper.CategoryMapper;
import com.sol.infrastructure.database.mongo.category.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.category.documents.CategoryDoc;
import com.sol.domain.category.entity.CategoryEntity;
import com.sol.domain.category.port.CategoryRepository;
import com.sol.domain.category.port.filters.CategoryFilters;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoCategoryRepository implements CategoryRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(CategoryDoc.class);

    private final MongoTemplate mongoTemplate;
    private final CategoryMapper mapper;

    @Override
    public CategoryEntity save(CategoryEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<CategoryEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, CategoryDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<CategoryEntity> find(CategoryFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, CategoryDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), CategoryDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), CategoryDoc.class);
    }
}