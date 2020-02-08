package com.github.crehn.mara.boundary;

import com.github.crehn.mara.Logged;
import com.github.crehn.mara.api.Meeting;
import com.github.crehn.mara.api.MeetingId;
import com.github.crehn.mara.control.MeetingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Logged
@RestController
public class MeetingBoundary {

    @Autowired
    private MeetingFacade facade;

    @PutMapping("/meetings/{meetingId}")
    public void createMeeting(@PathVariable("meetingId") MeetingId meetingId,
            @RequestBody Meeting meeting,
            @RequestHeader("User") String user) {
        facade.createMeeting(meetingId, meeting, user);
    }

}
