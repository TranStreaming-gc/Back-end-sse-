package org.example.sseexample.Controller;

import org.example.sseexample.EventSource;
import org.example.sseexample.Session.UserSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class AlarmController {

    @GetMapping(value = "/sse/{username}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents(@PathVariable String username) {
        EventSource eventSource = new EventSource();
        UserSession.addUser(username, eventSource); // 사용자 세션 추가

        return eventSource.getEventStream()
                .doOnCancel(() -> UserSession.removeUser(username)); // 연결 종료 시 세션 제거
    }

    @PostMapping("/send-event/{username}")
    public String sendEvent(@PathVariable String username, @RequestBody String message) {
        EventSource eventSource = UserSession.getUserSessions().get(username);
        if (eventSource != null) {
            eventSource.sendEvent(message);
            return "Event sent to " + username;
        }
        return "User not found";
    }
}