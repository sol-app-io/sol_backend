package com.sol.client.auth.v1;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import ru.foodtechlab.lib.auth.integration.core.authorizartion.AuthorizationServiceFacade;
import ru.foodtechlab.lib.auth.integration.proxy.api.authorization.v1.controllers.AuthorizationProxyApiController;

@RestController
@Component
public class BasicAuthorizationProxyController extends AuthorizationProxyApiController {

    public BasicAuthorizationProxyController(AuthorizationServiceFacade authorizationServiceFacade) {
        super(authorizationServiceFacade);
    }
}
