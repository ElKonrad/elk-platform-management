package pl.pollub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.docker.DockerFacade;
import pl.pollub.monitoring.dto.ApplicationAdded;

@RestController
@RequestMapping("/api/dev/docker")
@RequiredArgsConstructor
class DockerController {

    private final DockerFacade dockerFacade;

    @GetMapping(value = "start")
    public ResponseEntity<ApplicationAdded> startDocker() {
        dockerFacade.start();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "stop")
    public ResponseEntity<ApplicationAdded> stopDocker() {
        dockerFacade.stop();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "restart")
    public ResponseEntity<ApplicationAdded> restartDocker() {
        dockerFacade.restart();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
