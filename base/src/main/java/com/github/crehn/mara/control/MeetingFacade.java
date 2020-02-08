package com.github.crehn.mara.control;

import com.github.crehn.mara.api.Meeting;
import com.github.crehn.mara.api.MeetingId;
import com.github.crehn.mara.convert.CalendarProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeetingFacade {

    @Autowired
    private CalendarProvider calendarProvider;

    public void createMeeting(MeetingId meetingId, Meeting meeting, String user) {
        calendarProvider.createMeeting(meeting, user);

        meeting.getAttendees().forEach(
                attendee -> calendarProvider.createMeeting(meeting, attendee));
    }
}
