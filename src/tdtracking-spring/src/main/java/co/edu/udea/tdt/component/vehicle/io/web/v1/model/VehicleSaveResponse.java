package co.edu.udea.tdt.component.vehicle.io.web.v1.model;

import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleSaveResponse {

    private Long id;

    private String name;

    private String description;

    private String licensePlate;

    private String model;

    private String brand;

    private Boolean active;

    private Integer totalTrips;

    private List<String> trips;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static VehicleSaveResponse fromModel(Vehicle vehicle){
        return VehicleSaveResponse.builder().id(vehicle.getId())
                .name(vehicle.getName()).description(vehicle.getDescription())
                .licensePlate(vehicle.getLicensePlate()).model(vehicle.getModel())
                .active(vehicle.getActive()).licensePlate(vehicle.getLicensePlate())
                .createDate(vehicle.getCreateDate()).updateDate(vehicle.getUpdateDate())
                .totalTrips(vehicle.getTripIds().size()).brand(vehicle.getBrand())
                .build();
    }
}
