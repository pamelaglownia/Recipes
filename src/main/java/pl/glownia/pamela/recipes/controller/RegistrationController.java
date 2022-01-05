package pl.glownia.pamela.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.repository.UserRepository;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public void register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @GetMapping("/api/details")
    public void getUserDetails(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        System.out.println("Username: " + details.getUsername());
        System.out.println("User has authorities/roles: " + authentication.getAuthorities());
    }
}