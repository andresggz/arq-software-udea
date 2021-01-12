package co.edu.udea.tdt.component.vehicle.io.web.v1.model;

import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleListResponse {

    private Long id;

    private String name;

    private String description;

    private String detail;

    private String iconId;

    private String bannerId;

    private Boolean active;

    private Integer totalTrips;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static VehicleListResponse fromModel(Vehicle vehicle){
        return VehicleListResponse.builder().id(vehicle.getId())
                .name(vehicle.getName()).description(vehicle.getDescription())
                .detail(vehicle.getDetail()).iconId(vehicle.getIconId())
                .bannerId(vehicle.getBannerId())
                .createDate(vehicle.getCreateDate())
                .updateDate(vehicle.getUpdateDate())
                .build();
    }
}
