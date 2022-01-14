package com.sol.client.viewUser.v1.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChangeViewSortRequest {
    protected List<String> views = new ArrayList<>();
}
