package com.example.cocina_service;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class TriggerSinkTest {

    @Test
    void shouldEmitMessage() {
        // GIVEN
        TriggerSink triggerSink = new TriggerSink();

        // WHEN + THEN
        StepVerifier.create(triggerSink.flux())
                .then(() -> triggerSink.emit("hola mundo"))
                .expectNext("hola mundo")
                .thenCancel()
                .verify();
    }
}