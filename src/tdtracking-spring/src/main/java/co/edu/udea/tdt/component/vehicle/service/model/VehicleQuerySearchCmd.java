package co.edu.udea.tdt.component.vehicle.service.model;

import lombok.*;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleQuerySearchCmd {

    private String name;

    private Boolean active;

    private Boolean isLinkedToRoute;
}
