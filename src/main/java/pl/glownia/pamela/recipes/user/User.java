package pl.glownia.pamela.recipes.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pl.glownia.pamela.recipes.recipe.Recipe;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String role;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<Recipe> recipes = new HashSet<>();
}