package com.sol.domain.view.entity;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.TaskEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class View {

    public enum Sort {
LIST
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
        public enum Type {
            SLOT_TIME,
            NOTIFICATION,
            DEADLINE_EXACT_DATE,
            HAS_REPEAT,
            DEADLINE_RELATIVE_DATE,
            TASK_IS_OVERDUE,
            CLOSED,
            FROM_SPACE,
            DEADLINE_CHANGED_FEW_TIMES;
        }

        protected String id;
        protected Type type;
        protected String valueString;
        protected LocalDateTime valueDate;
        protected Boolean valueBool;

        private Boolean didPassSlotTime(TaskEntity taskEntity) {
            return false;
        }

        private Boolean didPassNotification(TaskEntity taskEntity) {
            return false;

        }

        private Boolean didPassDeadlineExactDate(TaskEntity taskEntity) {
            return false;

        }

        private Boolean didPassHasRepeat(TaskEntity taskEntity) {
            return false;

        }

        private Boolean didPassDeadlineRelativeDate(TaskEntity taskEntity) {
            return false;

        }

        private Boolean didPassTaskIsOverdue(TaskEntity taskEntity) {
            return false;

        }

        private Boolean didPassClosed(TaskEntity taskEntity) {
            return false;

        }

        private Boolean didPassFromSpace(TaskEntity taskEntity) {
            return false;

        }

        private Boolean didPassDeadlineChangedFewTimes(TaskEntity taskEntity) {
            return false;
        }

        public Boolean didPass(TaskEntity taskEntity) {
            switch (type){
                case SLOT_TIME:
                    return didPassSlotTime(taskEntity);
                case NOTIFICATION:
                    return didPassNotification(taskEntity);
                case DEADLINE_EXACT_DATE:
                    return didPassDeadlineExactDate(taskEntity);
                case HAS_REPEAT:
                    return didPassHasRepeat(taskEntity);
                case DEADLINE_RELATIVE_DATE:
                    return didPassDeadlineRelativeDate(taskEntity);
                case TASK_IS_OVERDUE:
                    return didPassTaskIsOverdue(taskEntity);
                case CLOSED:
                    return didPassClosed(taskEntity);
                case FROM_SPACE:
                    return didPassFromSpace(taskEntity);
                case DEADLINE_CHANGED_FEW_TIMES:
                    return didPassDeadlineChangedFewTimes(taskEntity);
            }
            return false;
        }
    }

    protected Icon icon = new Icon();
    protected String title = "";
    protected String description = "";
    protected AddedType addedType = AddedType.MANUALLY;
    protected DisplayMode displayMode = DisplayMode.LIST;
    protected Sort sortType = Sort.LIST;
    protected List<Params> params = new ArrayList<>();

    public enum CheckTaskType {
        NOT_IN_VIEW, NEED_ADD_AUTO, NEED_ADD_MANUALLY;
    }

    public CheckTaskType checkTask(TaskEntity taskEntity) {
        if(params == null || params.size() == 0) return CheckTaskType.NOT_IN_VIEW;

        for (Params param : params) {
            Boolean paramPass = param.didPass(taskEntity);
            if(paramPass == false) return CheckTaskType.NOT_IN_VIEW;
        }

        if(addedType.equals(AddedType.MANUALLY)) return CheckTaskType.NEED_ADD_MANUALLY;

        return CheckTaskType.NEED_ADD_AUTO;
    }
}
