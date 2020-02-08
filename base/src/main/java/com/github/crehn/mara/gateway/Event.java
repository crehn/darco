package com.github.crehn.mara.gateway;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    private String title;
    private String dtstart;
    private String dtend;
}
