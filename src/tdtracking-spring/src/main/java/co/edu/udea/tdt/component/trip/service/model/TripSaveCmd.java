package co.edu.udea.tdt.component.trip.service.model;

import co.edu.udea.tdt.component.trip.model.Trip;
import co.edu.udea.tdt.component.trip.model.TripLevel;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripSaveCmd {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 700)
    private String description;

    @NotNull
    private TripLevel level;

    @Future
    private LocalDateTime releaseDate;

    public static Trip toModel(@NotNull TripSaveCmd tripToCreateCmd){
        return Trip.builder().name(tripToCreateCmd.getName())
                .description(tripToCreateCmd.getDescription())
                .level(tripToCreateCmd.getLevel())
                .releaseDate(tripToCreateCmd.getReleaseDate())
                .build();
    }
}
