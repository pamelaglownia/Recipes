package pl.glownia.pamela.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.glownia.pamela.recipes.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT recipe FROM Recipe recipe WHERE lower(recipe.category) LIKE lower(:category)")
    Iterable<Recipe> findByCategoryIgnoreCase(@Param("category") String category);
}