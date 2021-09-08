package com.sol.domain.task.entity;

import java.util.ArrayList;
import java.util.List;

public enum TaskStatus {
    OPEN, DONE;

    public static List<TaskStatus> all(){
        List<TaskStatus> res = new ArrayList<>();
        res.add(OPEN);
        res.add(DONE);
        return res;
    }
    public static List<TaskStatus> open(){
        List<TaskStatus> res = new ArrayList<>();
        res.add(OPEN);
        return res;
    }
    public static List<TaskStatus> done(){
        List<TaskStatus> res = new ArrayList<>();
        res.add(DONE);
        return res;
    }
}
