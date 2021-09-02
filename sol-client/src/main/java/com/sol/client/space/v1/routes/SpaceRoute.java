package com.sol.client.space.v1.routes;

import com.sol.client.base.BaseApiRoutes;

public class SpaceRoute {
    public static final String ROOT = BaseApiRoutes.V1 + "/space";
    public static final String SINGLETON = ROOT + "/{id}";
    public static final String SORT_CHANGE = ROOT + "/sort-change";
}
