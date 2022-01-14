package com.sol.client.viewUser.v1.request;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.view.entity.View;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserViewRequest {
    protected Icon icon = new Icon();
    protected String title = "";
    protected String description = "";
    protected View.AddedType addedType = View.AddedType.MANUALLY;
    protected View.DisplayMode displayMode = View.DisplayMode.LIST;
    protected View.Sort sortType = View.Sort.CUSTOM;
    protected View.ViewType viewType = View.ViewType.CUSTOM;

}
