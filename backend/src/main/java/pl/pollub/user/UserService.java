package pl.pollub.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User registerNewUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);
}
