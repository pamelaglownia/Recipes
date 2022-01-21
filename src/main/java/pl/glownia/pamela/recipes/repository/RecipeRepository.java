package pl.glownia.pamela.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.glownia.pamela.recipes.model.Recipe;
import pl.glownia.pamela.recipes.model.User;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByNameContainingIgnoreCaseOrderByCreationDateDesc(String name);

    List<Recipe> findByCategoryIgnoreCaseOrderByCreationDateDesc(String category);

    Set<Recipe> findByUser(User user);

}