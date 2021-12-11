package com.sol.infrastructure.database.mongo.taskInView.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sol.infrastructure.database.mongo.taskInView.mapper.TaskInViewMapper;
import com.sol.infrastructure.database.mongo.taskInView.query.FindWithFiltersQuery;
import com.sol.infrastructure.database.mongo.taskInView.documents.TaskInViewDoc;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.taskInView.port.filters.TaskInViewFilters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoTaskInViewRepository implements TaskInViewRepository {
    private static final String collectionName = CollectionNameUtils.getCollectionName(TaskInViewDoc.class);

    private final MongoTemplate mongoTemplate;
    private final TaskInViewMapper mapper;

    @Override
    public TaskInViewEntity save(TaskInViewEntity entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity), collectionName));
    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), collectionName).getDeletedCount();

        return deleteCount > 0;
    }

    @Override
    public Optional<TaskInViewEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, TaskInViewDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public SearchResult<TaskInViewEntity> find(TaskInViewFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, TaskInViewDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), TaskInViewDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), TaskInViewDoc.class);
    }

    @Override
    public void removeByViewId(String viewId) {
        Criteria criteria = Criteria.where("viewId").is(viewId);
        mongoTemplate.remove(new Query(criteria), TaskInViewDoc.class);
    }

    @Override
    public Optional<TaskInViewEntity> findOne(String taskId, String viewId) {
        Criteria criteria = Criteria.where("viewId").is(viewId).and("taskId").is(taskId);
        Query query = new Query(criteria);
        query.limit(1);
        return Optional.ofNullable(mongoTemplate.findOne(query, TaskInViewDoc.class)).map(mapper::inverseMap);
    }

    @Override
    public Long count(String viewId) {
        Criteria criteria = Criteria.where("viewId").is(viewId);
        return mongoTemplate.count(new Query(criteria), TaskInViewDoc.class);
    }

    @Override
    public List<TaskInViewEntity> findByTaskId(String taskId) {
        Criteria criteria = Criteria.where("taskId").is(taskId);
        Query query = new Query(criteria);
        query.with(Sort.by(Sort.Direction.ASC, "sortNum"));
        return mongoTemplate.find(query, TaskInViewDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList());
    }
}