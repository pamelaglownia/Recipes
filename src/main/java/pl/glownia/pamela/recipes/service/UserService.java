package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.repository.UserRepository;

import java.security.Principal;
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

    public String getCurrentUser(Principal principal) {
        return principal.getName();
    }

    public User getByEmail(String username) {
        return userRepository.getByEmail(username);
    }
}