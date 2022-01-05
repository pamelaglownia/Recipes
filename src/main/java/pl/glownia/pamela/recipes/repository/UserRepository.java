package pl.glownia.pamela.recipes.repository;

import org.springframework.stereotype.Component;
import pl.glownia.pamela.recipes.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepository {
    final private Map<String, User> users = new ConcurrentHashMap<>();

    public User findUserByEmail(String username) {
        return users.get(username);
    }

    public void save(User user) {
        users.put(user.getUsername(), user);
    }
}