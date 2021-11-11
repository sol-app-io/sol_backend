package com.sol.domain.base.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateUtils {
    public static LocalDateTime convert(Long millisecFrom1970, Integer timezoneHours) {
        if (millisecFrom1970 != null && timezoneHours != null) {
            return LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(millisecFrom1970),
                    ZoneId.ofOffset("UTC", ZoneOffset.ofHours(timezoneHours)
                    )
            );
        }
        return null;
    }

    public static Long convert(LocalDateTime dateTime, Integer timezoneHours) {
        return dateTime != null && timezoneHours != null ?
                dateTime.toInstant(ZoneOffset.ofHours(timezoneHours)).toEpochMilli() :
                null;
    }

    public static Long convert(Instant dateTime, Integer timezoneHours) {
        return dateTime != null && timezoneHours != null ?
                dateTime.toEpochMilli() :
                null;
    }

    public static Long range(Long start, Long end){
        if(start == null || end == null) return 0l;

        return end - start;
    }
}
