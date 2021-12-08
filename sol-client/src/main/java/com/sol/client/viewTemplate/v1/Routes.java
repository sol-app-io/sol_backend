package com.sol.client.viewTemplate.v1;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/view-template";
    public static final String SINGLETON = ROOT + "/{id}";
    public static final String BULK_ADD = ROOT + "/{id}/bulk-add-by-admin";
}
