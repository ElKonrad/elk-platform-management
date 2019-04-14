package pl.pollub.logstash;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogstashFacade {

    private final LogstashService logstashService;
    private final LogstashConfigurator logstashConfigurator;

    public void injectSomeLogEntries() {
        logstashService.injectSomeLogEntriesToLogstashLocalhostTCP();
    }

    public void buildConfiguration(List<ApplicationAdded> apps) {
        String configuration = logstashConfigurator.createConfiguration(apps);
        System.out.println("HHHHHHHHHHHHHHHH");
        System.out.println(configuration);
        logstashService.persistConfig(configuration);
        logstashService.createLogstashSinceDbFiles(apps);
    }
}
