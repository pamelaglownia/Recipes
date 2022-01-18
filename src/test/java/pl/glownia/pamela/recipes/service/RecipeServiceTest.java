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
    @Mock
    private RecipeRepository recipeRepository;
    private RecipeService underTestService;

    @BeforeEach
    void setUp() {
        underTestService = new RecipeService(recipeRepository);
    }

    @Test
    void getCookbook() {
        //when
        underTestService.getCookbook();
        //then
        verify(recipeRepository).findAll();
    }
}