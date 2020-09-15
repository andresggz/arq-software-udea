package co.com.arqsoft.demodecouplingspring.component.user.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    Role role;

    @NotNull
    String names;

    @NotNull
    String lastNames;

    @NotNull
    LocalDate dateOfBirth;

    @NotNull
    LocalDateTime createDate;

    @NotNull
    LocalDateTime updateDate;

}
