package pl.glownia.pamela.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.service.UserService;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/api/registration")
    public void register(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }

    @GetMapping("/api/users")
    @ResponseBody
    public Set<String> getAllUsersEmails() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/current-user")
    @ResponseBody
    public String getCurrentUserEmail() {
        return userService.getCurrentUserEmail();
    }
}