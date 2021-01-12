package co.edu.udea.tdt.component.fleet.service.model;

import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleIdSaveCmd {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    public static VehicleIdFleet toModel(VehicleIdSaveCmd vehicleIdToRegister){
        return VehicleIdFleet.builder().id(vehicleIdToRegister.getId())
                .build();
    }
}
