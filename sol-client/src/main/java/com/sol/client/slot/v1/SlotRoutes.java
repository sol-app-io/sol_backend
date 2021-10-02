package com.sol.client.slot.v1;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class SlotRoutes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/slot";
    public static final String SINGLETON = ROOT + "/{id}";

}
