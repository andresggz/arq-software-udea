package co.edu.udea.tdt.component.user.service.model;

import lombok.*;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserQuerySearchCmd {

    private String names;

    private String lastNames;

    private String username;

    private String primaryEmailAddress;
}
