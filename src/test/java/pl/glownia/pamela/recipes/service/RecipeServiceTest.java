package pl.glownia.pamela.recipes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.glownia.pamela.recipes.model.Recipe;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.repository.RecipeRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //instead of AutoCloseable
class RecipeServiceTest {
    @Mock //we don't use Autowired real repo
    private RecipeRepository recipeRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private RecipeService underTestService;

/*
    we don't need it because of annotation above class
    @AfterEach
    void tearDown() throws Exception {
    autoCloseable.close(); //close resources after the test
    }
*/

    @Test
    void canGetCookbook() {
        //when
        underTestService.getCookbook();
        //then
        verify(recipeRepository).findAll();
    }

    @Test
    void canAddNewRecipe() {
        //given
        Recipe summerIceTea = prepareData();
        //when
        underTestService.addRecipe(summerIceTea);
        //then
        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeRepository).save(recipeArgumentCaptor.capture());

        Recipe capturedRecipe = recipeArgumentCaptor.getValue();

        assertThat(capturedRecipe).isEqualTo(summerIceTea);
    }

    @Test
    void canUpdateRecipe() {
        //given
        Recipe summerIceTea = prepareData();
        when(recipeRepository.findById(5L))
                .thenReturn(Optional.ofNullable(summerIceTea));

        //when
        assert summerIceTea != null;
        underTestService.updateRecipe(5L, summerIceTea);

        //then
        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeRepository).save(recipeArgumentCaptor.capture());
        Recipe capturedRecipe = recipeArgumentCaptor.getValue();
        Assertions.assertEquals(summerIceTea, capturedRecipe);
    }

    private Recipe prepareData() {
        User harry = User.builder()
                .email("harry@gmail.com")
                .password("password456!")
                .role("USER")
                .build();
        return Recipe.builder()
                .name("Summer ice tea")
                .category("beverage")
                .description("Perfect drink for hot summer evening")
                .ingredients("tea, ice cubes, honey, lemon slice, orange slice, ")
                .directions("Prepare tea in standard way, add honey and cool it. " +
                        "Add ice cubes, fruit slices and relax sunny evening.")
                .user(harry).build();
    }
}