package org.example.sseexample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EventSource {
    private final Sinks.Many<String> sink = Sinks.many().multicast().directAllOrNothing();
    private final Flux<String> eventStream = sink.asFlux().share();

    public Flux<String> getEventStream() {
        return eventStream;
    }

    public void sendEvent(String message) {
        sink.tryEmitNext(message);
    }
}