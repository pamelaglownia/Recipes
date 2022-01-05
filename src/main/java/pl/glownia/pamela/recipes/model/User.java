package pl.glownia.pamela.recipes.model;


import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Email
    String username;
    String password;
    String role;


}
