package pl.pollub.infrastructure.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pollub.docker.DockerFacade;
import pl.pollub.user.UserCreator;
import pl.pollub.user.UserService;
import pl.pollub.user.dto.UserRequest;

import javax.annotation.PreDestroy;

@Component
public class AppLifeCycleManager implements CommandLineRunner{

    @Autowired
    private UserService userService;

    @Autowired
    private DockerFacade dockerFacade;

    @Override
    public void run(String... strings) throws Exception {
        UserRequest testUser = UserRequest.builder()
                .username("testowy")
                .password("Pass123@")
                .email("testowy@gmail.com")
                .build();
        userService.registerNewUser(UserCreator.from(testUser));

        dockerFacade.start();
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        dockerFacade.stop();
    }
}
