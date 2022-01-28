package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.exception.UserForbiddenException;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Set<String> getAllUsers() {
        return userRepository.findAll().stream().map(User::getEmail).collect(Collectors.toSet());
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean isAuthorOfRecipe(User user) {
        if (!user.getEmail().equals(getCurrentUserEmail())) {
            throw new UserForbiddenException();
        }
        return true;
    }

    public User getByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}