package co.edu.udea.tdt.component.trip.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 45)
    private String name;

    @Lob
    @NotNull
    @NotBlank
    @Size(min = 3, max = 700)
    private String description;

    @NotNull
    private TripLevel level;

    private String approved;

    private Boolean active;

    @Future
    private LocalDateTime releaseDate;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
