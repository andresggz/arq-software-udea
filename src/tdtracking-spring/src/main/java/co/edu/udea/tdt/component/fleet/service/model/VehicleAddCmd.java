package co.edu.udea.tdt.component.fleet.service.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleAddCmd {

    @NotNull
    private Long vehicleId;

}
