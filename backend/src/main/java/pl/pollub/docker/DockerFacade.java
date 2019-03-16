package pl.pollub.docker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DockerFacade {

    private final DockerService dockerService;

    public void start() {
        dockerService.up();
    }

    public void stop() {
        dockerService.stop();
    }

    public void restart() {
        dockerService.restart();
    }
}
