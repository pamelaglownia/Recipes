package pl.glownia.pamela.recipes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.glownia.pamela.recipes.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}