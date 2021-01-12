package co.edu.udea.tdt.component.vehicle.service.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripAddCmd {

    @NotNull
    private Long tripId;

}
