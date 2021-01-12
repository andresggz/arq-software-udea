package co.edu.udea.tdt.component.vehicle.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "vehicles")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Vehicle {

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
    @NotBlank
    @Size(min = 3)
    private String licensePlate;

    private String model;

    private String brand;

    private Boolean active;

    private Boolean linkedToRoute;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @ManyToMany
    private Set<TripIdVehicle> tripIds;

}
