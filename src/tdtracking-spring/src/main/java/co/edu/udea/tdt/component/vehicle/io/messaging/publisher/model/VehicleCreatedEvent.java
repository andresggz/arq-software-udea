package co.edu.udea.tdt.component.vehicle.io.messaging.publisher.model;

import lombok.*;


@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VehicleCreatedEvent {

    private Long id;

    private String name;

    private String description;
}
