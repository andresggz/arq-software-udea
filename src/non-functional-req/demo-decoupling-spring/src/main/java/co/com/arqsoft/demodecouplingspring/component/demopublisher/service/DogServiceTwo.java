package co.com.arqsoft.demodecouplingspring.component.demopublisher.service;

import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.model.DogCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DogServiceTwo {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void handleGodCreated(DogCreatedEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        logger.info("Dog serviceTwo method 1...");
    }

    @EventListener
    public void handleGodCreated2(DogCreatedEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        logger.info("Dog serviceTwo method 2...");
    }
}
