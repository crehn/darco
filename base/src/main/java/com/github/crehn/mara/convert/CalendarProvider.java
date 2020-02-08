package com.github.crehn.mara.convert;

import com.github.crehn.mara.api.Meeting;
import com.github.crehn.mara.gateway.CalendarGateway;
import com.github.crehn.mara.gateway.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class CalendarProvider {

    @Autowired
    private CalendarGateway gateway;


    public void createMeeting(Meeting meeting, String user) {
        gateway.createEvent(toEvent(meeting), user);
    }

    private Event toEvent(Meeting meeting) {
        // for some reason the calendar system uses iCalendar-like timestamps and assumes Berlin time zone
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss")
                .withZone(ZoneId.of("Europe/Berlin"));
        return Event.builder()
                .title(meeting.getName())
                .dtstart(formatter.format(meeting.getStartTime()))
                .dtend(formatter.format(meeting.getEndTime()))
                .build();
    }
}
