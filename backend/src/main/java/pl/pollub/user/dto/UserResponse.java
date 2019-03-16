package pl.pollub.user.dto;

import lombok.Builder;
import lombok.Getter;
import pl.pollub.user.User;

import java.util.Date;

@Builder
@Getter
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private User.Role role;
    private Boolean enabled;
    private Boolean isBanned;
    private Date createdDate;
}
