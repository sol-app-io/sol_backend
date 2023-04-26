package com.sol.client.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/not-secure/api/v1/test")
    public Test test(){
        return new Test();
    }

}
