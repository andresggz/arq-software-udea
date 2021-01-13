package co.edu.udea.tdt.component.fleet.service.model;

import co.edu.udea.tdt.component.fleet.model.Fleet;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FleetSaveCmd {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 700)
    private String description;


    public static Fleet toModel(@NotNull FleetSaveCmd vehicleToCreateCmd){
        return Fleet.builder().name(vehicleToCreateCmd.getName())
                .description(vehicleToCreateCmd.getDescription())
                .build();
    }
}
