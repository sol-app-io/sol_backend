package com.sol.domain.view.entity;

import com.sol.domain.base.entity.Icon;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class View {

    public enum Sort {

    }

    public enum DisplayMode {
        LIST,
        TIMELINE,
        CONCENTRATE
    }

    public enum Type {
        CUSTOM, PARAMS
    }

    public enum AddedType {
        MANUALLY,
        AUTO
    }

    @Data
    public static class Params {
        protected enum Type {
            SLOT_TIME,
            NOTIFICATION,
            DEADLINE_EXACT_DATE,
            HAS_REPEAT,
            DEADLINE_RELATIVE_DATE,
            TASK_IS_OVERDUE,
            CLOSED,
            FROM_SPACE,
            DEADLINE_CHANGED_FEW_TIMES
        }

        protected String id;
        protected Type type;
        protected String valueString;
        protected LocalDateTime valueDate;
        protected Boolean valueBool;
    }

    protected Icon icon = new Icon();
    protected String title;
    protected String description;
    protected AddedType addedType = AddedType.MANUALLY;
    protected DisplayMode displayMode = DisplayMode.LIST;
    protected Sort sortType;
    protected List<Params> params = new ArrayList<>();
}
