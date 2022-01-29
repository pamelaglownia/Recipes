package pl.glownia.pamela.recipes.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    UserService underTestService;

    @Test
    void canAddUser() {
        //given
        User julia = prepareData();
        //when
        underTestService.addUser(julia);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(julia);
    }

    @Test
    void canGetAllUsers() {
        //when
        underTestService.getAllUsers();
        //then
        verify(userRepository).findAll();
    }

    @Test
    void canFindUserByEmail() {
        //given
        User julia = prepareData();
        //when
        underTestService.getByEmail(julia.getEmail());
        //then
        verify(userRepository).findByEmail("julia@gmail.com");
    }

    private User prepareData() {
        return User.builder()
                .email("julia@gmail.com")
                .password("pass123!")
                .role("USER")
                .build();
    }
}