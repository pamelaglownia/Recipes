package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.repository.UserRepository;
import pl.glownia.pamela.recipes.security.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.getByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " wasn't find.");
        }
        return new UserPrincipal(user);
    }
}