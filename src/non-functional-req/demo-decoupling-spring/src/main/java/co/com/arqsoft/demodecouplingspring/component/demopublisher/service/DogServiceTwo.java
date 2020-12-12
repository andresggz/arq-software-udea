package co.com.arqsoft.demodecouplingspring.component.demopublisher.service;

import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.model.DogCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DogServiceTwo {

    @EventListener
    public void handleGodCreated(DogCreatedEvent event){
        System.out.println("Dog serviceTwo method 1...");
    }

    @EventListener
    public void handleGodCreated2(DogCreatedEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Dog serviceTwo method 2...");
    }
}
