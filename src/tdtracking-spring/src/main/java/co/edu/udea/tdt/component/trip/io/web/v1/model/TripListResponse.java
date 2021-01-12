package co.edu.udea.tdt.component.trip.io.web.v1.model;

import co.edu.udea.tdt.component.trip.model.Trip;
import co.edu.udea.tdt.component.trip.model.TripLevel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripListResponse {

    private Long id;

    private String name;

    private String description;

    private TripLevel level;

    private String approved;

    private Boolean active;

    private LocalDateTime releaseDate;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static TripListResponse fromModel(Trip trip){
        return TripListResponse.builder().id(trip.getId())
                .name(trip.getName()).description(trip.getDescription())
                .level(trip.getLevel()).approved(trip.getApproved())
                .releaseDate(trip.getReleaseDate())
                .createDate(trip.getCreateDate())
                .updateDate(trip.getUpdateDate())
                .build();
    }
}
