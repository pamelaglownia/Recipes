package pl.glownia.pamela.recipes.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.glownia.pamela.recipes.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository underTestRepository;

    @AfterEach
    void tearDown() {
        underTestRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfUserWithChosenEmailExists() {
        //given
        String email = "harry@gmail.com";
        User harry = User.builder()
                .email(email)
                .password("password456!")
                .role("USER")
                .build();
        underTestRepository.save(harry);

        //when
        User expected = underTestRepository.getByEmail(email);

        //then
        assertThat(expected).isEqualTo(harry);
    }

    @Test
    void itShouldCheckIfUserWithChosenEmailDoesNotExists() {
        //given
        String email = "polly@gmail.com";
        User harry = User.builder()
                .email("harry@gmail.com")
                .password("password456!")
                .role("USER")
                .build();
        underTestRepository.save(harry);

        //when
        User expected = underTestRepository.getByEmail(email);

        //then
        assertThat(expected).isNotEqualTo(harry);
    }
}