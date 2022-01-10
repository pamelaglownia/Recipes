package pl.glownia.pamela.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@RestController
public class RegistrationController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }

    @GetMapping("/api/user")
    @ResponseBody
    public String getCurrentUser(Principal principal) {
        return userService.getCurrentUser(principal);
    }

    @GetMapping("/api/users/all")
    @ResponseBody
    public Set<String> getAllUsers() {
        return userService.getAllUsers();
    }
}