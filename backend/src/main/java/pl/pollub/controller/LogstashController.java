package pl.pollub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.logstash.LogstashFacade;
import pl.pollub.monitoring.dto.ApplicationAdded;

@RestController
@RequestMapping("/api/dev/logstash")
@RequiredArgsConstructor
class LogstashController {

    private final LogstashFacade logstashFacade;

    @GetMapping(value = "injectlogs")
    public ResponseEntity<ApplicationAdded> injectSomeLogEntries() {
        logstashFacade.injectSomeLogEntries();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
