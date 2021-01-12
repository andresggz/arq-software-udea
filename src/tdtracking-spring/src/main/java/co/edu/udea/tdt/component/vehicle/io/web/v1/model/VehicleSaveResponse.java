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

    private String detail;

    private String iconId;

    private String bannerId;

    private Boolean active;

    private Integer totalTrips;

    private List<String> trips;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static VehicleSaveResponse fromModel(Vehicle vehicle){
        return VehicleSaveResponse.builder().id(vehicle.getId())
                .name(vehicle.getName()).description(vehicle.getDescription())
                .detail(vehicle.getDetail()).iconId(vehicle.getIconId())
                .active(vehicle.getActive()).detail(vehicle.getDetail())
                .createDate(vehicle.getCreateDate()).updateDate(vehicle.getUpdateDate())
                .totalTrips(vehicle.getTripIds().size())
                .build();
    }
}
