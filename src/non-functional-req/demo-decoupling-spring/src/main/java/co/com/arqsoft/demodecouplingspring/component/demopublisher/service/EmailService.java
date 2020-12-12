package co.com.arqsoft.demodecouplingspring.component.demopublisher.service;

import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.model.DogCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailService {

    @EventListener
    public void sendWelcomeDog(DogCreatedEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(String.format("Sending a welcome email to %s. Email %s", event.getName(), event.getEmail()));
    }
}
