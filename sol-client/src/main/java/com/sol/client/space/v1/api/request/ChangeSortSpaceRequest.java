package com.sol.client.space.v1.api.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

@Getter
@Setter
public class ChangeSortSpaceRequest {
    private List<String> spaces;
}
