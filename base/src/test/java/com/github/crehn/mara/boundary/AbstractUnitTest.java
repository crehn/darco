package com.github.crehn.mara.boundary;

import com.github.crehn.mara.control.MeetingFacade;
import com.github.crehn.mara.convert.CalendarProvider;
import com.github.crehn.mara.gateway.CalendarGateway;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.spy;

public class AbstractUnitTest {

    @Spy
    @InjectMocks
    private MeetingFacade meetingFacade = spy(MeetingFacade.class);

    @Spy
    @InjectMocks
    private CalendarProvider calendarProvider = spy(CalendarProvider.class);

    @Mock
    CalendarGateway calendarGateway;

}
