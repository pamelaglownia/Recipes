package pl.glownia.pamela.recipes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Recipe has to have name")
    private String name;

    @NotBlank(message = "Provide short recipe description")
    private String description;

    @NotBlank(message = "Are you sure that recipe doesn't have ingredients?")
    private String ingredients;

    @NotBlank(message = "Provide recipe direction")
    private String directions;

    public Recipe(String name, String description, String ingredients, String directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}