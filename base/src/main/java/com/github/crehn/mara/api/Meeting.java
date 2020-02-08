package com.github.crehn.mara.api;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Meeting {
    private String name;
    private Instant startTime;
    private Instant endTime;
}
