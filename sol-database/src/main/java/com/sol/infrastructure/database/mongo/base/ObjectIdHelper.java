package com.sol.infrastructure.database.mongo.base;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ObjectIdHelper {
    public static ObjectId mapOrDie(String id) {
        if (id != null && ObjectId.isValid(id)) return new ObjectId(id);
        return null;
    }

    public static List<ObjectId> mapOrDie(List<String> ids) {
        List<ObjectId> result = new ArrayList<>();
        for (String id : ids) {
            if (id != null && ObjectId.isValid(id)) result.add(new ObjectId(id));
        }
        return result;
    }

    public static String toString(ObjectId id) {
        if (id != null) return id.toString();
        return null;
    }

    public static List<String> toString(List<ObjectId> ids) {
        if(ids == null) return new ArrayList<>();

        List<String> result = new ArrayList<>();
        for (ObjectId id : ids) {
            if (id != null) result.add(id.toString());
        }
        return result;
    }
}
