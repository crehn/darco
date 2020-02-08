package com.github.crehn.mara.boundary;

import com.github.crehn.mara.api.Meeting;
import com.github.crehn.mara.api.MeetingId;
import com.github.crehn.mara.gateway.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MeetingBoundaryTest extends AbstractUnitTest {

    @InjectMocks
    private MeetingBoundary boundary;

    @Test
    void shouldCreateMeeting() {
        Meeting meeting = Meeting.builder()
                .name("meeting name")
                .startTime(Instant.parse("2020-02-08T16:00:00Z"))
                .endTime(Instant.parse("2020-02-08T16:30:00Z"))
                .build();
        MeetingId meetingId = MeetingId.createRandom();
        boundary.createMeeting(meetingId, meeting, "user");

        Event event = captureCalendarEvent("user");
        assertCalendarEvent(event);
    }

    private Event captureCalendarEvent(String calendarOwner) {
        ArgumentCaptor<Event> captor = ArgumentCaptor.forClass(Event.class);
        verify(calendarGateway).createEvent(captor.capture(), eq(calendarOwner));
        return captor.getValue();
    }

    private void assertCalendarEvent(Event event) {
        assertThat(event.getTitle()).isEqualTo("meeting name");
        assertThat(event.getDtstart()).isEqualTo("20200208T170000");
        assertThat(event.getDtend()).isEqualTo("20200208T173000");
    }

    @Test
    void shouldCreateMeetingWithAttendees() {
        Meeting meeting = Meeting.builder()
                .name("meeting name")
                .startTime(Instant.parse("2020-02-08T16:00:00Z"))
                .endTime(Instant.parse("2020-02-08T16:30:00Z"))
                .attendee("Isaac Newton")
                .attendee("Albert Einstein")
                .attendee("Stephen Hawking")
                .attendee("Commander Data")
                .build();
        MeetingId meetingId = MeetingId.createRandom();
        boundary.createMeeting(meetingId, meeting, "user");

        assertCalendarEvent(captureCalendarEvent("user"));
        assertCalendarEvent(captureCalendarEvent("Isaac Newton"));
        assertCalendarEvent(captureCalendarEvent("Albert Einstein"));
        assertCalendarEvent(captureCalendarEvent("Stephen Hawking"));
        assertCalendarEvent(captureCalendarEvent("Commander Data"));
    }

}
