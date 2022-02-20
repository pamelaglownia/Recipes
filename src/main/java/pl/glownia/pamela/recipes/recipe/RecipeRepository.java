package pl.glownia.pamela.recipes.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.glownia.pamela.recipes.user.User;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT recipe FROM Recipe recipe " +
            "WHERE LOWER(recipe.name) LIKE LOWER(CONCAT('%',?1,'%')) " +
            "ORDER BY recipe.creationDate DESC")
    List<Recipe> findByPartOfName(String name);

    @Query("SELECT recipe FROM Recipe recipe " +
            "WHERE LOWER(recipe.category) = LOWER(?1)" +
            "ORDER BY recipe.creationDate DESC")
    List<Recipe> findByCategory(String category);

    Set<Recipe> findByUser(User user);
}