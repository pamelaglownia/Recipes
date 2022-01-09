package pl.glownia.pamela.recipes.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint(name = "user_email_unique", columnNames = "email")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Email(message = "Invalid email")
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email")
    String email;

    @NotBlank
    @Size(min = 8, message = "Password should have minimum 8 characters.")
    String password;

    String role;
    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER
    )
    List<Recipe> recipes = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}