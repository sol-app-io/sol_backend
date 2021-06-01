package com.sol.client.space.v1.api.response;

import com.sol.domain.base.entity.Icon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SpaceResponse {
    private String id;
    private String title;
    private Icon icon = new Icon();
    private Integer sortNum;
}
