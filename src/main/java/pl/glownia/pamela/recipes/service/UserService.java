package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.exception.UserForbiddenException;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Set<String> getAllUsers() {
        return userRepository.findAll().stream().map(User::getEmail).collect(Collectors.toSet());
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean isAuthorOfRecipe(User user) {
        if (!user.getEmail().equals(getCurrentUser())) {
            throw new UserForbiddenException();
        }
        return true;
    }

    public User getByEmail(String username) {
        return userRepository.getByEmail(username);
    }
}