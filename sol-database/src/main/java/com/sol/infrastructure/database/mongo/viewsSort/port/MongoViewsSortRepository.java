package com.sol.infrastructure.database.mongo.viewsSort.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.sol.domain.viewsSort.port.filters.ViewsSortBySolTypeFilters;
import com.sol.infrastructure.database.mongo.viewsSort.query.ViewsSortBySolTypeQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.viewsSort.mapper.ViewsSortMapper;
import com.sol.infrastructure.database.mongo.viewsSort.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.viewsSort.documents.ViewsSortDoc;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import com.sol.domain.viewsSort.port.filters.ViewsSortFilters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoViewsSortRepository implements ViewsSortRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(ViewsSortDoc.class);

    private final MongoTemplate mongoTemplate;
    private final ViewsSortMapper mapper;

    @Override
    public ViewsSortEntity save(ViewsSortEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<ViewsSortEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, ViewsSortDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<ViewsSortEntity> find(ViewsSortFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ViewsSortDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ViewsSortDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), ViewsSortDoc.class);
    }

    @Override
    public void removeByViewId(String viewId) {
        Criteria criteria = Criteria.where("viewsId").is(viewId);
        while (true){
            Query query = new Query(criteria);
            query.limit(100);
            List<ViewsSortDoc> result = mongoTemplate.find(query, ViewsSortDoc.class);
            if(result.size() == 0) break;

            for(ViewsSortDoc viewsSortDoc : result){
                viewsSortDoc.getViewsId().remove(viewId);
                mongoTemplate.save(viewsSortDoc);
            }
        }
    }

    @Override
    public Optional<ViewsSortEntity> find(ViewsSortBySolTypeFilters filters) {
        System.out.println("RUN Query query = new ViewsSortBySolTypeQuery(filters).getQuery();");
        Query query = new ViewsSortBySolTypeQuery(filters).getQuery();
        System.out.println("RUN return Optional.ofNullable(mongoTemplate.findById(query, ViewsSortDoc.class)).map(mapper::inverseMap);");
        return Optional.ofNullable(mongoTemplate.findById(query, ViewsSortDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public List<ViewsSortEntity> findBySolUser(String solUserId) {
        Criteria criteria = Criteria.where("ownerId").is(solUserId);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, ViewsSortDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList());
    }
}