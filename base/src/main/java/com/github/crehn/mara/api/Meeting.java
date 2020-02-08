package com.github.crehn.mara.api;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class Meeting {
    private String name;
    private Instant startTime;
    private Instant endTime;

    @Singular
    private List<String> attendees;
}
