package pl.glownia.pamela.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.recipes.user.User;
import pl.glownia.pamela.recipes.user.UserService;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/registration")
    public void register(@Valid @RequestBody User user) {
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