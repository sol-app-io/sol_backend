package com.sol.client.solUser.v1.routes;

import com.rcore.rest.api.commons.routes.BaseRoutes;
import com.sol.client.base.BaseApiRoutes;

public class SignUpRoutes {
    public static final String ROOT = BaseRoutes.NOT_SECURE + BaseRoutes.API + BaseRoutes.V1 + "/sign-up";

    public static final String EMAIL = ROOT + "/email";
}
