package pl.glownia.pamela.recipes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @NotBlank
    private String name;
    //    @NotBlank
    private String description;
    private String[] ingredients;
    private String[] directions;

    public Recipe(String name, String description, String[] ingredients, String[] directions) {
//    public Recipe(@NotBlank String name, @NotBlank String description, @NotEmpty(message = "Are you sure that recipe doesn't have ingredients?") String[] ingredients, @NotEmpty(message = "Recipe must have directions") String[] directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getDirections() {
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

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }
}