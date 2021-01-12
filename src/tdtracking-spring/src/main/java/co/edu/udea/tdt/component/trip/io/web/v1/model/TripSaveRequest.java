package co.edu.udea.tdt.component.trip.io.web.v1.model;

import co.edu.udea.tdt.component.trip.model.TripLevel;
import co.edu.udea.tdt.component.trip.service.model.TripSaveCmd;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripSaveRequest {

    @NotNull(message = "name has to be present.")
    @NotBlank(message = "name can't be blank.")
    @Size(min = 3, max = 45, message = "Please enter at least {min} and at most {max} characters.")
    private String name;

    @NotNull(message = "description has to be present.")
    @NotBlank(message = "description can't be blank.")
    @Size(min = 3, max = 700, message = "Please enter at least {min} and at most {max} characters.")
    private String description;

    @NotNull(message = "level has to be present.")
    private TripLevel level;

    @Future(message = "releaseDate has to be in the future.")
    private LocalDateTime releaseDate;

    public static TripSaveCmd toModel(TripSaveRequest tripToCreate) {
        return TripSaveCmd.builder().name(tripToCreate.getName())
                .description(tripToCreate.getDescription())
                .level(tripToCreate.getLevel())
                .releaseDate(tripToCreate.getReleaseDate())
                .build();
    }
}
