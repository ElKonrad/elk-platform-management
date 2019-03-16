package pl.pollub.user;

import pl.pollub.user.dto.UserRequest;

public class UserCreator {

    public static User from(UserRequest request) {
        return User.builder()
                   .username(request.getUsername())
                   .password(request.getPassword())
                   .email(request.getEmail())
                   .build();
    }
}
