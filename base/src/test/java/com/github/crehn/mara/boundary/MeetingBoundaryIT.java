package com.github.crehn.mara.boundary;

import com.github.crehn.mara.api.Meeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
public class MeetingBoundaryIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldCreateMeeting() {
        webTestClient.put()
                .uri(uriBuilder -> uriBuilder.path("/meetings/{meetingId}").build(UUID.randomUUID()))
                .bodyValue(Meeting.builder()
                        .name("my Meeting")
                        .startTime(Instant.parse("2020-02-08T18:00:00Z"))
                        .endTime(Instant.parse("2020-02-08T18:30:00Z"))
                        .build())
                .header("User", "user")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}
