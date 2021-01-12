package co.edu.udea.tdt.component.fleet.io.web.v1.model;

import co.edu.udea.tdt.component.fleet.service.model.VehicleAddCmd;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VehicleAddRequest {

    @NotNull
    private Long vehicleId;

    public static VehicleAddCmd toModel(VehicleAddRequest vehicleToAdd){
        return VehicleAddCmd.builder()
                .vehicleId(vehicleToAdd.getVehicleId())
                .build();
    }
}
