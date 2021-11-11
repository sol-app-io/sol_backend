package com.sol.client.auth.v1;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.proxy.api.credential.v1.controllers.InitCredentialController;

@RestController
@Component
public class InitCredentialProxyController extends InitCredentialController {
    public InitCredentialProxyController(CredentialServiceFacade credentialServiceFacade) {
        super(credentialServiceFacade);
    }
}
