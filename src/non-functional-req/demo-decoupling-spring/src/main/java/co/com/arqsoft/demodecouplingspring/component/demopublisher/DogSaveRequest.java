package co.com.arqsoft.demodecouplingspring.component.demopublisher;

import co.com.arqsoft.demodecouplingspring.component.demopublisher.service.model.DogCreatedEvent;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DogSaveRequest {

    @Size(min = 3)
    private String name;

    private Integer age;

    @Email
    private String email;

    @Size(min = 2)
    private String country;

    public static DogCreatedEvent toModel(DogSaveRequest dog){
       return DogCreatedEvent.builder().name(dog.getName())
                .country(dog.getCountry())
                .age(dog.getAge())
                .email(dog.getEmail())
                .build();
    }
}
