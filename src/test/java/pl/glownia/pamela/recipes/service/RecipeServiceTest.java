package pl.glownia.pamela.recipes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.glownia.pamela.recipes.repository.RecipeRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) //instead of AutoCloseable
class RecipeServiceTest {
    @Mock //we don't use Autowired real repo
    private RecipeRepository recipeRepository;
    private RecipeService underTestService;

    @BeforeEach
    void setUp() {
//      autoCloseable = MockitoAnnotations.openMocks(this); - we don't need it because of annotation above class
        underTestService = new RecipeService(recipeRepository);
    }
/*
  * we don't need it because of annotation above class
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
}