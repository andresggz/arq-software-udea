package co.edu.udea.tdt.component.fleet.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VehicleIdFleet {

    @Id
    private Long id;
}
