package pl.pollub.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.pollub.user.dto.UserResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Size(min = 6, max = 32)
    @Column(unique = true)
    private String username;

    @Size(min = 60, max = 60)
    private String password;

    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    private Boolean enabled;

    @NotNull
    @ColumnDefault("false")
    private Boolean isBanned;

    private Date createdDate;

    public enum Role {
        ROLE_USER, ROLE_ADMIN;
    }

    public UserResponse toResponseDto() {
        return UserResponse.builder()
                           .id(id)
                           .username(username)
                           .email(email)
                           .enabled(enabled)
                           .isBanned(isBanned)
                           .createdDate(createdDate)
                           .role(role)
                           .build();
    }
}
