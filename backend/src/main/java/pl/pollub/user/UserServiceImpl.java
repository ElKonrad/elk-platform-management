package pl.pollub.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pollub.infrastructure.security.AppPrincipal;

import javax.transaction.Transactional;
import java.util.Date;

import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return AppPrincipal.builder()
                           .user(user)
                           .build();
    }

    @Override
    @Transactional
    public User registerNewUser(User user) {

        if (userWithSpecifiedEmailExists(user.getEmail())) {
            throw new EmailExistsException(user.getEmail());
        } else if (userWithSpecifiedUsernameExists(user.getUsername())) {
            throw new UserUsernameExistException(user.getUsername());
        }

        return userRepository.save(User.builder()
                                       .username(user.getUsername())
                                       .password(passwordEncoder.encode(user.getPassword()))
                                       .email(user.getEmail())
                                       .role(User.Role.ROLE_USER)
                                       .enabled(true)
                                       .isBanned(false)
                                       .createdDate(new Date())
                                       .build());
    }


    @Override
    public User getUserById(Long id) {

        return ofNullable(userRepository.findOne(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByUsername(String username) {

        return ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    private Boolean userWithSpecifiedEmailExists(String email) {

        return userRepository.countUsersByEmail(email) > 0;
    }

    private Boolean userWithSpecifiedUsernameExists(String username) {

        return userRepository.countUsersByUsername(username) > 0;
    }
}
