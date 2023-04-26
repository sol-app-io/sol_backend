package com.sol.client.auth.v1;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade;

@RestController
@Component
public class InitCredentialProxyController{//} extends InitCredentialController {
    public InitCredentialProxyController(CredentialServiceFacade credentialServiceFacade) {
//        super(credentialServiceFacade);
    }
}
