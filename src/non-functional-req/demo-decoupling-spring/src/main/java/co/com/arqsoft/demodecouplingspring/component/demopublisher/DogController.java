package co.com.arqsoft.demodecouplingspring.component.demopublisher;

import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.DogServiceOne;
import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.DogServiceTwo;
import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.EmailService;
import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.model.DogCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/v1/dogs", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DogController {

    private final ApplicationEventPublisher publisher;

    private final DogServiceOne dogServiceOne;
    private final DogServiceTwo dogServiceTwo;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody DogSaveRequest dogToCreate) throws InterruptedException {


        DogCreatedEvent dogCreatedToPublish = DogSaveRequest.toModel(dogToCreate);
        System.out.println("Dog created.");

        dogServiceOne.handleGodCreated(dogCreatedToPublish);
        dogServiceTwo.handleGodCreated(dogCreatedToPublish);
        dogServiceTwo.handleGodCreated2(dogCreatedToPublish);
        emailService.sendWelcomeDog(dogCreatedToPublish);

        publisher.publishEvent(dogCreatedToPublish);

        return ResponseEntity.ok().build();
    }
}
