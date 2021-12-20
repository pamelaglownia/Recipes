package pl.glownia.pamela.recipes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Recipe name can't be empty")
    private String name;

    @NotBlank(message = "Fill the category of recipe")
    private String category;

    private LocalDateTime date;

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
        this.date = LocalDateTime.now();
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}