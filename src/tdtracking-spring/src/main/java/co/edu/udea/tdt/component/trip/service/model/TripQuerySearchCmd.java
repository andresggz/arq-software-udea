package co.edu.udea.tdt.component.trip.service.model;

import co.edu.udea.tdt.component.trip.model.TripLevel;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripQuerySearchCmd {

    private String name;

    private TripLevel level;

    private Boolean active;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime releaseDateLessThan;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime releaseDateGreaterThan;
}
