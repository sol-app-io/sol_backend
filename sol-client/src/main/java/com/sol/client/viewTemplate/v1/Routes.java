package com.sol.client.viewTemplate.v1;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/view-template";
    public static final String SINGLETON = ROOT + "/{id}";
    public static final String ADD_PARAM = SINGLETON + "/param/add";
    public static final String EDIT_PARAM = SINGLETON + "/param/edit";
    public static final String DELETE_PARAM = SINGLETON + "/param/delete";
    public static final String BULK_ADD = ROOT + "/{id}/bulk-add-by-admin";
}
