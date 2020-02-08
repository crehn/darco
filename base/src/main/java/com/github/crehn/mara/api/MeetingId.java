package com.github.crehn.mara.api;

import lombok.Value;

import java.util.UUID;

@Value
public class MeetingId {
    UUID value;

    public static MeetingId createRandom() {
        return new MeetingId(UUID.randomUUID());
    }

    public static MeetingId valueOf(String string) {
        return new MeetingId(UUID.fromString(string));
    }

    public String toString() {
        return "" + value;
    }
}
