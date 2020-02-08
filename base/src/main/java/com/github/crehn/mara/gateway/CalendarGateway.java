package com.github.crehn.mara.gateway;

import com.github.crehn.mara.Logged;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Logged
@Component
public class CalendarGateway {
    public void createEvent(Event event, String user) {
        // do whatever is necessary to create this event in the calendar
        // this will involve a call over the network and consumes some time:
        simulateNetworkLatency();
    }

    @SneakyThrows
    private void simulateNetworkLatency() {
        Thread.sleep(300);
    }
}
