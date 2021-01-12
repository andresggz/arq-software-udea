package co.edu.udea.tdt.component.trip.io.messaging.publisher.model;

import lombok.*;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripCreatedEvent {

    private Long id;

    private String name;

    private String description;

    private String level;

    private String iconId;

    private Boolean active;
}
