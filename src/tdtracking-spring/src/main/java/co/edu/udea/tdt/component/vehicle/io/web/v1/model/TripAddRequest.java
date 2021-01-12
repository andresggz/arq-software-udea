package co.edu.udea.tdt.component.vehicle.io.web.v1.model;

import co.edu.udea.tdt.component.vehicle.service.model.TripAddCmd;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripAddRequest {

    @NotNull
    private Long tripId;

    public static TripAddCmd toModel(TripAddRequest tripToAdd){
        return TripAddCmd.builder().tripId(tripToAdd.tripId).build();
    }
}
