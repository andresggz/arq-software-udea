package co.com.arqsoft.demodecouplingspring.component.demopublisher.service;

import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.model.DogCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void sendWelcomeDog(DogCreatedEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        logger.info(String.format("Sending a welcome email to %s. Email %s", event.getName(), event.getEmail()));
    }
}
