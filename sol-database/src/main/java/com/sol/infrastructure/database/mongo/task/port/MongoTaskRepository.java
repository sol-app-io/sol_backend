package com.sol.infrastructure.database.mongo.task.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.sol.infrastructure.database.mongo.base.ObjectIdHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.task.mapper.TaskMapper;
import com.sol.infrastructure.database.mongo.task.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.task.documents.TaskDoc;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.task.port.filters.TaskFilters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoTaskRepository implements TaskRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(TaskDoc.class);

    private final MongoTemplate mongoTemplate;
    private final TaskMapper mapper;

    @Override
    public TaskEntity save(TaskEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<TaskEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, TaskDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<TaskEntity> find(TaskFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, TaskDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), TaskDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), TaskDoc.class);
    }

    @Override
    public List<TaskEntity> findBySpaceId(String spaceId) {
        Criteria criteria = Criteria.where("spaceId").is(ObjectIdHelper.mapOrDie(spaceId)).and("parentTaskId").exists(false);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, TaskDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList());
    }
}