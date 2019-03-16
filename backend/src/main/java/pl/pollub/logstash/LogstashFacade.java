package pl.pollub.logstash;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogstashFacade {

    private final LogstashService logstashService;

    public void injectSomeLogEntries() {
        logstashService.injectSomeLogEntriesToLogstashLocalhostTCP();
    }
}
