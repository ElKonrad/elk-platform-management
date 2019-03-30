package pl.pollub.monitoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.logstash.LogstashFacade;
import pl.pollub.monitoring.dto.AddApplication;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class MonitoredAppFacade {

    private final MonitoredAppService monitoredAppService;
    private final LogstashFacade logstashFacade;

    public ApplicationAdded addAppAndRebuildConfig(AddApplication application) {
        MonitoredApp created = monitoredAppService.create(application);
        List<ApplicationAdded> apps = monitoredAppService.getAll()
                                                         .stream()
                                                         .map(MonitoredApp::dto)
                                                         .collect(toList());
        logstashFacade.buildConfiguration(apps);
        return created.dto();
    }
}
