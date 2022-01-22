package pl.glownia.pamela.recipes.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.glownia.pamela.recipes.model.Recipe;
import pl.glownia.pamela.recipes.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    RecipeRepository underTestRepository;

    @AfterEach
    public void tearDown() {
        underTestRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfRecipeWithChosenNameExists() {
        //given
        User julia = User.builder()
                .email("julia@gmail.com")
                .password("pass123!")
                .role("USER")
                .build();
        Recipe winterCoffee = Recipe.builder()
                .name("Winter coffee")
                .category("beverage")
                .description("Simple coffee for cold winter")
                .ingredients("coffee beans, , milk, cinnamon, honey, star anise, cloves")
                .directions("Prepare coffee in your favourite cup, add milk, honey and all spices.").user(julia).build();
        underTestRepository.save(winterCoffee);
        List<Recipe> cookbook = new ArrayList<>();
        cookbook.add(winterCoffee);

        //when
        List<Recipe> expected = underTestRepository.findByNameContainingIgnoreCaseOrderByCreationDateDesc("winter");

        //then
        assertThat(expected).isEqualTo(cookbook);
    }

    @Test
    void itShouldCheckIfRecipeWithChosenNameDoesNotExists() {
        //given
        User rob = User.builder()
                .email("rob@gmail.com")
                .password("pass123!")
                .role("USER")
                .build();
        Recipe summerIceTea = Recipe.builder()
                .name("Summer ice tea")
                .category("beverage")
                .description("Perfect drink for hot summer evening")
                .ingredients("tea, ice cubes, honey, lemon slice, orange slice, ")
                .directions("Prepare tea in standard way, add honey and cool it. Add ice cubes, fruit slices and relax sunny evening.").user(rob).build();
        underTestRepository.save(summerIceTea);
        List<Recipe> cookbook = new ArrayList<>();
        cookbook.add(summerIceTea);

        //when
        List<Recipe> expected = underTestRepository.findByNameContainingIgnoreCaseOrderByCreationDateDesc("winter");

        //then
        assertThat(expected).isNotEqualTo(cookbook);
    }

    @Test
    public void itShouldCheckIfRecipeFromChosenCategoryExists() {
        //given
        String recipeCategory = "beverage";
        User julia = User.builder()
                .email("julia@gmail.com")
                .password("pass123!")
                .role("USER")
                .build();
        Recipe winterCoffee = Recipe.builder()
                .name("Winter coffee")
                .category(recipeCategory)
                .description("Simple coffee for cold winter")
                .ingredients("coffee beans, , milk, cinnamon, honey, star anise, cloves")
                .directions("Prepare coffee in your favourite cup, add milk, honey and all spices.").user(julia).build();
        underTestRepository.save(winterCoffee);
        List<Recipe> cookbook = new ArrayList<>();
        cookbook.add(winterCoffee);

        //when
        List<Recipe> expected = underTestRepository.findByCategoryIgnoreCaseOrderByCreationDateDesc(recipeCategory);

        //then
        assertThat(expected).isEqualTo(cookbook);
    }

    @Test
    void itShouldCheckIfRecipeFromChosenCategoryDoesNotExists() {
        //given
        String recipeCategory = "beverage";
        User julia = User.builder()
                .email("julia@gmail.com")
                .password("pass123!")
                .role("USER")
                .build();
        Recipe winterCoffee = Recipe.builder()
                .name("Winter coffee")
                .category(recipeCategory)
                .description("Simple coffee for cold winter")
                .ingredients("coffee beans, , milk, cinnamon, honey, star anise, cloves")
                .directions("Prepare coffee in your favourite cup, add milk, honey and all spices.").user(julia).build();
        underTestRepository.save(winterCoffee);
        List<Recipe> cookbook = new ArrayList<>();
        cookbook.add(winterCoffee);

        //when
        List<Recipe> expected = underTestRepository.findByCategoryIgnoreCaseOrderByCreationDateDesc("food");

        //then
        assertThat(expected).isNotEqualTo(cookbook);
    }

    @Test
    void itShouldGetAllUserRecipes() {
        //given
        User julia = User.builder()
                .email("julia@gmail.com")
                .password("pass123!")
                .role("USER")
                .build();
        Recipe winterCoffee = Recipe.builder()
                .name("Winter coffee")
                .category("beverage")
                .description("Simple coffee for cold winter")
                .ingredients("coffee beans, , milk, cinnamon, honey, star anise, cloves")
                .directions("Prepare coffee in your favourite cup, add milk, honey and all spices.")
                .user(julia)
                .build();
        underTestRepository.save(winterCoffee);
        Recipe iceCoffee = Recipe.builder()
                .name("Ice coffee")
                .category("beverage")
                .description("Simple coffee for sunny day")
                .ingredients("coffee beans, , milk, sugar, ice cubes")
                .directions("Prepare coffee with sugar in tall glass, add few ice cubes and milk.")
                .user(julia)
                .build();
        underTestRepository.save(iceCoffee);
        Set<Recipe> recipes = new HashSet<>();

        recipes.add(winterCoffee);
        recipes.add(iceCoffee);
        //when
        Set<Recipe> expected = underTestRepository.findByUser(julia);

        //then
        assertThat(expected).isEqualTo(recipes);
    }
}