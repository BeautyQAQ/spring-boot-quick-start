package com.quick.start.ddd.util;

import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.OffsetDateTime;

public interface OffsetDateTimes {
    static OffsetDateTime currentTime() {
        return OffsetDateTime.now().truncatedTo(MILLIS);
    }
}
