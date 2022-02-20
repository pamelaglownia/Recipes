package pl.glownia.pamela.recipes.recipe;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.glownia.pamela.recipes.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Recipe name can't be empty")
    private String name;

    @NotBlank(message = "Fill the category of recipe")
    private String category;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    @NotBlank(message = "Provide short recipe description")
    private String description;

    @NotBlank(message = "Are you sure that recipe doesn't contain ingredients?")
    private String ingredients;

    @NotBlank(message = "Provide recipe direction")
    private String directions;

    @ManyToOne
            (cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",
            referencedColumnName = "userId")
    private User user;
}