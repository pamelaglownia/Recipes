package pl.glownia.pamela.recipes.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Recipe name can't be empty")
    private String name;

    @NotBlank(message = "Fill the category of recipe")
    private String category;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationData;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    @NotBlank(message = "Provide short recipe description")
    private String description;

    @NotBlank(message = "Are you sure that recipe doesn't contain ingredients?")
    private String ingredients;

    @NotBlank(message = "Provide recipe direction")
    private String directions;

    public Recipe() {
    }

    public Recipe(String name, String category, String description, String ingredients, String directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}