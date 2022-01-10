package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.model.User;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " wasn't find.");
        }
        return new UserDetailsImpl(user);
    }
}