package co.edu.udea.tdt.component.fleet.io.web.v1.model;

import co.edu.udea.tdt.component.fleet.service.model.FleetSaveCmd;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FleetSaveRequest {

    @NotNull(message = "name has to be present.")
    @NotBlank(message = "name can't be blank.")
    @Size(min = 3, max = 45, message = "Please enter at least {min} and at most {max} characters.")
    private String name;

    @NotNull(message = "description has to be present.")
    @NotBlank(message = "description can't be blank.")
    @Size(min = 3, max = 700, message = "Please enter at least {min} and at most {max} characters.")
    private String description;

    public static FleetSaveCmd toModel(@NotNull FleetSaveRequest vehicleToCreate){
        return FleetSaveCmd.builder().name(vehicleToCreate.getName())
                .description(vehicleToCreate.getDescription())
                .build();
    }
}
