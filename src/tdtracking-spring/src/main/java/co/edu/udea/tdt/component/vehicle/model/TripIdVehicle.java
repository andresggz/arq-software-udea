package co.edu.udea.tdt.component.vehicle.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TripIdVehicle {

    @Id
    private Long id;
}
