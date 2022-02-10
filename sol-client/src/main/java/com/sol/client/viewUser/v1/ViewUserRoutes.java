package com.sol.client.viewUser.v1;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class ViewUserRoutes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/view-user";
    public static final String ADD_TASK_TO_VIEW = ROOT  + "/add-task";
    public static final String MY_ROOT = ROOT + "/root";
    public static final String FIND_VIEW_BY_TASK = ROOT + "/by-task";
    public static final String FIND_TASKS_BY_VIEW = ROOT + "/by-view";
    public static final String SINGLETON = ROOT + "/{id}";
    public static final String UPDATE_FULL = ROOT + "/{id}/update-full";
    public static final String PARAM_ADD = SINGLETON + "/param/add";
    public static final String PARAM_EDIT = SINGLETON + "/param/{paramId}/edit";
    public static final String PARAM_DELETE = SINGLETON + "/param/{paramId}delete";
    public static final String CHANGE_SORT = ROOT + "/sort-change";
}
