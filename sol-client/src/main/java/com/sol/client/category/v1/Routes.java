package com.sol.client.category.v1;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/category";
    public static final String SINGLETON = ROOT + "/{id}";
}
