package com.sol.client.task.v1;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/task";
    public static final String SINGLETON = ROOT + "/{id}";
    public static final String DONE = SINGLETON + "/done";
    public static final String OPEN = SINGLETON + "/open";
    public static final String PARENT = ROOT + "/parent";
    public static final String SPACE = ROOT + "/space";
}
