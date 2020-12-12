package co.com.arqsoft.demodecouplingspring.component.demopublisher.service;

import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.model.DogCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DogServiceOne {

    @EventListener
    public void handleGodCreated(DogCreatedEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Dog serviceOne method 1...");
    }
}
