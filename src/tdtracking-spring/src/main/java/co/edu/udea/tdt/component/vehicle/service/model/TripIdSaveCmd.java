package co.edu.udea.tdt.component.vehicle.service.model;

import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;
import lombok.*;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripIdSaveCmd {

    private Long id;

    private String name;

    private String description;

    private String level;

    private String iconId;

    private Boolean active;

    public static TripIdVehicle toModel(TripIdSaveCmd tripToRegister){
        return TripIdVehicle.builder().id(tripToRegister.getId())
                .build();
    }
}
