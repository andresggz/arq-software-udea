package co.edu.udea.tdt.component.vehicle.service.model;

import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleSaveCmd {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 700)
    private String description;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String licensePlate;

    private String model;

    private String brand;

    public static Vehicle toModel(@NotNull VehicleSaveCmd vehicleToCreateCmd){
        return Vehicle.builder().name(vehicleToCreateCmd.getName())
                .description(vehicleToCreateCmd.getDescription())
                .licensePlate(vehicleToCreateCmd.getLicensePlate())
                .brand(vehicleToCreateCmd.getBrand())
                .model(vehicleToCreateCmd.getModel())
                .build();
    }
}
