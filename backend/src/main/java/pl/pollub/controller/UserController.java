package pl.pollub.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.pollub.infrastructure.security.PrincipalProvider;
import pl.pollub.user.User;
import pl.pollub.user.UserCreator;
import pl.pollub.user.UserService;
import pl.pollub.user.dto.UserRequest;
import pl.pollub.user.dto.UserResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/users")
@AllArgsConstructor
class UserController {

    private UserService userService;
    private PrincipalProvider principalProvider;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid @NotNull UserRequest request) {

        User user = userService.registerNewUser(UserCreator.from(request));
        return new ResponseEntity<>(user.toResponseDto(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable String username) {

        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user.toResponseDto(), HttpStatus.OK);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isAuthenticated() {

        boolean isUserLogged = principalProvider.isUserLogged();
        return new ResponseEntity<>(isUserLogged, HttpStatus.OK);
    }

    @RequestMapping(value = "/loggedUsername", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getLoggedUsername() {

        String username = principalProvider.getLoggedUsername();
        return new ResponseEntity<>(UserResponse.builder()
                                                .username(username)
                                                .build(),
                                    HttpStatus.OK);
    }
}
