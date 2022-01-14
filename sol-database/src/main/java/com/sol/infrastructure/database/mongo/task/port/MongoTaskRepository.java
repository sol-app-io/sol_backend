package com.sol.infrastructure.database.mongo.task.port;

import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.view.entity.View;
import com.sol.infrastructure.database.mongo.base.ObjectIdHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
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
    public List<TaskEntity> findBySpaceId(String spaceId, List<TaskStatus> statuses) {
        Criteria criteria = Criteria
                .where("spaceId").is(ObjectIdHelper.mapOrDie(spaceId))
                .and("parentTaskId").exists(false)
                .and("status").in(statuses);
        Query query = new Query(criteria);
        query = query.with(Sort.by(Sort.Direction.ASC, "sortNum"));
        return mongoTemplate.find(query, TaskDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList());
    }

    @Override
    public List<TaskEntity> findByUserId(String userId) {
        Criteria criteria = Criteria
                .where("ownerId").is(ObjectIdHelper.mapOrDie(userId))
                .and("status").is(TaskStatus.OPEN);
        Query query = new Query(criteria);
//        query = query.with(Sort.by(Sort.Direction.ASC, "sortNum"));
        return mongoTemplate.find(query, TaskDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList());
    }

    @Override
    public List<TaskEntity> findByParentId(String parentId, List<TaskStatus> statuses) {
        Criteria criteria = Criteria
                .where("parentTaskId").is(ObjectIdHelper.mapOrDie(parentId))
                .and("status").in(statuses);
        ;
        Query query = new Query(criteria);
        query = query.with(Sort.by(Sort.Direction.ASC, "sortNum"));
        return mongoTemplate.find(query, TaskDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList());
    }

    @Override
    public Long countBySpaceId(String spaceId, List<TaskStatus> statuses) {
        Criteria criteria = Criteria
                .where("spaceId").is(ObjectIdHelper.mapOrDie(spaceId))
                .and("parentTaskId").exists(false)
                .and("status").in(statuses);
        ;
        Query query = new Query(criteria);
        return mongoTemplate.count(query, TaskDoc.class);
    }

    @Override
    public Long countByParentId(String parentId, List<TaskStatus> statuses) {
        Criteria criteria = Criteria
                .where("parentTaskId").is(ObjectIdHelper.mapOrDie(parentId))
                .and("status").in(statuses);
        ;
        Query query = new Query(criteria);
        return mongoTemplate.count(query, TaskDoc.class);
    }


    private Criteria suggestCriteria(String ownerId, String viewId, View view){
        Boolean hasClosed = false;

        for (View.Params param : view.getParams()) {
            if (param.getType().equals(View.Params.Type.CLOSED)) hasClosed = true;
            if (param.getType().equals(View.Params.Type.TASK_IS_OVERDUE)) {
                hasClosed = false;
                break;
            }
        }

        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(Criteria.where("ownerId").is(ObjectIdHelper.mapOrDie(ownerId)));
        criteriaList.add(Criteria.where("viewIds").is(ObjectIdHelper.mapOrDie(viewId)));
        criteriaList.add(Criteria.where("status").is(hasClosed == true ? TaskStatus.DONE : TaskStatus.OPEN));

        for (View.Params param : view.getParams()) {
            if (param.getType().equals(View.Params.Type.SLOT_TIME)) {
                criteriaList.add(Criteria.where("slotsMilliseconds").gt(0));
            }
            if (param.getType().equals(View.Params.Type.NOTIFICATION)) {
                // criteriaList.add(Criteria.where("slotsMilliseconds").gt(0));
            }
            if (param.getType().equals(View.Params.Type.DEADLINE_EXACT_DATE) && param.getValueDate() != null) {
                LocalDateTime dt = param.getValueDate();
                dt = dt.with(LocalTime.of(23, 59, 59));
                criteriaList.add(Criteria.where("deadline").lt(dt));
            }
            if (param.getType().equals(View.Params.Type.HAS_REPEAT)) {
                if (param.getValueBool() == true) {
                    criteriaList.add(Criteria.where("repeatTaskConfId").exists(true));
                } else {
                    criteriaList.add(Criteria.where("repeatTaskConfId").exists(false));
                }

            }
            if (param.getType().equals(View.Params.Type.DEADLINE_RELATIVE_DATE) && param.getValueString() != null) {
                try {
                    Integer value = Integer.valueOf(param.getValueString());
                    LocalDateTime dt = LocalDateTime.now().plusDays(value);
                    dt = dt.with(LocalTime.of(23, 59, 59));
                    criteriaList.add(Criteria.where("deadline").lt(dt));
                } catch (Exception e) {
                }
            }
            if (param.getType().equals(View.Params.Type.TASK_IS_OVERDUE)) {
                if (param.getValueBool() == true) {
                    LocalDateTime dt = LocalDateTime.now().with(LocalTime.of(23, 59, 59));
                    criteriaList.add(Criteria.where("deadline").gt(dt));
                } else {
                    LocalDateTime dt = LocalDateTime.now().with(LocalTime.of(23, 59, 59));
                    criteriaList.add(Criteria.where("deadline").lte(dt));
                }
            }
            if (param.getType().equals(View.Params.Type.FROM_SPACE) && param.getValueString() != null) {
                criteriaList.add(Criteria.where("spaceId").is(ObjectIdHelper.mapOrDie(param.getValueString())));
            }
            if (param.getType().equals(View.Params.Type.DEADLINE_CHANGED_FEW_TIMES)) {
                if (param.getValueBool() == true) {
                    criteriaList.add(Criteria.where("deadlineChangeFewTimes").is(true));
                } else {
                    criteriaList.add(Criteria.where("deadlineChangeFewTimes").is(false));
                }
            }
        }

        Criteria[] criteria = new Criteria[criteriaList.size()];
        Criteria criteriaForQuery = new Criteria();
        criteriaForQuery.andOperator(criteriaList.toArray(criteria));

        return criteriaForQuery;
    }

    @Override
    public List<TaskEntity> findSuggest(String ownerId, String viewId, View view) {
        Query query = new Query(suggestCriteria(ownerId, viewId, view));
        query.limit(50);
        //query.withHint()
        return mongoTemplate.find(query, TaskDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList());
    }

    @Override
    public Long countSuggest(String ownerId, String viewId, View view) {
        Query query = new Query(suggestCriteria(ownerId, viewId, view));

        //query.withHint()

        return mongoTemplate.count(query, TaskDoc.class);
    }
}