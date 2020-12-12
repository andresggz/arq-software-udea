package co.com.arqsoft.demodecouplingspring.component.demopublisher.service.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DogCreatedEvent{

    @Size(min = 3)
    private String name;

    @Size(min = 10)
    private Integer age;

    @Email
    private String email;

    @Size(min = 2)
    private String country;
}
