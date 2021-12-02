package pl.glownia.pamela.recipes;

public class Recipe {
    private final String name;
    private final String description;
    private final String ingredients;
    private final String directions;

    public Recipe(String name, String description, String ingredients, String directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
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
}