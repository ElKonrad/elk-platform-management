package pl.pollub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.pollub.user.UserCreator;
import pl.pollub.user.UserService;
import pl.pollub.user.dto.UserRequest;

@SpringBootApplication
public class ElkManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElkManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            UserRequest testUser = UserRequest.builder()
                                              .username("testowy")
                                              .password("Pass123@")
                                              .email("testowy@gmail.com")
                                              .build();
            userService.registerNewUser(UserCreator.from(testUser));
        };
    }


}
