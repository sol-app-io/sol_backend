package com.sol.client.viewUser.v1.mappers;

import com.sol.client.viewUser.v1.response.TaskInViewResponse;
import com.sol.client.viewUser.v1.response.ViewUserResponse;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewUser.entity.ViewUserEntity;

public class ViewUserResponseMapper {
    public ViewUserResponse map(ViewUserEntity entity) {
        return ViewUserResponse.builder()
                .id(entity.getId())
                .ownerId(entity.getOwnerId())
                .createdFromTemplateId(entity.getCreatedFromTemplateId())
                .hasNewTaskToAdd(entity.getHasNewTaskToAdd())
                .hasTaskAdded(entity.getHasTaskAdded())
                .view(entity.getView())
                .canEdit(entity.getCanEdit())
                .build();
    }

    public TaskInViewResponse map(TaskInViewEntity task) {
        return TaskInViewResponse.builder()
                .viewId(task.getViewId())
                .sortNum(task.getSortNum())
                .build();
    }

}
