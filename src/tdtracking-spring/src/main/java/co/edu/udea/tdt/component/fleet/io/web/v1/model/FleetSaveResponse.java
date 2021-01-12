package co.edu.udea.tdt.component.fleet.io.web.v1.model;

import co.edu.udea.tdt.component.fleet.model.Fleet;
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
public class FleetSaveResponse {

    private Long id;

    private String name;

    private String description;

    private String iconId;

    private Boolean active;

    private Integer totalVehicles;

    private List<String> vehicles;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static FleetSaveResponse fromModel(Fleet fleet){
        return FleetSaveResponse.builder().id(fleet.getId())
                .name(fleet.getName()).description(fleet.getDescription())
                .totalVehicles(fleet.getVehicleIds().size())
                .createDate(fleet.getCreateDate()).updateDate(fleet.getUpdateDate())
                .build();
    }
}
