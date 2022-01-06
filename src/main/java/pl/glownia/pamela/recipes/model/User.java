package pl.glownia.pamela.recipes.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank
    @Email(message = "Invalid email")
    String username;

    @NotBlank
    @Size(min = 8, message = "Password should have minimum 8 characters.")
    String password;

    String role;
}