package com.example.cocina_service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class TriggerSink {

    private final Sinks.Many<String> sink =
            Sinks.many().multicast().onBackpressureBuffer();

    public void emit(String msg) {
        sink.tryEmitNext(msg);
    }

    public Flux<String> flux() {
        return sink.asFlux();
    }
}
