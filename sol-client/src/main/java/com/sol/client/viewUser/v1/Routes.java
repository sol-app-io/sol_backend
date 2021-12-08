package com.sol.client.viewUser.v1;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/view-user";
    public static final String MY_ROOT = BaseRoutes.API + BaseRoutes.V1 + "/root";
    public static final String SINGLETON = ROOT + "/{id}";
}
