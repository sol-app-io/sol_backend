package com.sol.client.project;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @ApiOperation(value = "dsfdd")
    @GetMapping(value = "/not-secure/api/v1/test")
    public Test test(){
        return new Test();
    }

}
