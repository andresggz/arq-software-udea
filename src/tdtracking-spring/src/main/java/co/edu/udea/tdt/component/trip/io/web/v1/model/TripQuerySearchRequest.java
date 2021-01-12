package co.edu.udea.tdt.component.trip.io.web.v1.model;

import co.edu.udea.tdt.component.trip.model.TripLevel;
import co.edu.udea.tdt.component.trip.service.model.TripQuerySearchCmd;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripQuerySearchRequest {

    private String name;

    private TripLevel level;

    private Boolean active;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime releaseDateLessThan;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime releaseDateGreaterThan;

    public static TripQuerySearchCmd toModel(TripQuerySearchRequest queryCriteria){
        return TripQuerySearchCmd.builder().name(queryCriteria.getName())
                    .level(queryCriteria.getLevel()).active(queryCriteria.getActive())
                    .releaseDateLessThan(queryCriteria.getReleaseDateLessThan())
                    .releaseDateGreaterThan(queryCriteria.getReleaseDateGreaterThan())
                    .build();
    }
}
