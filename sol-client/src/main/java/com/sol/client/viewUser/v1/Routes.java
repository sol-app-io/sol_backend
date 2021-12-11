package com.sol.client.viewUser.v1;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/view-user";
    public static final String MY_ROOT = ROOT + "/root";
    public static final String FIND_VIEW_BY_TASK = ROOT + "/by-task";
    public static final String SINGLETON = ROOT + "/{id}";
}
