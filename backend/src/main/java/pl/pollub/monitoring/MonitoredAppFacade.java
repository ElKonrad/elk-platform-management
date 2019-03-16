package pl.pollub.monitoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.monitoring.dto.AddApplication;
import pl.pollub.monitoring.dto.ApplicationAdded;

@Component
@RequiredArgsConstructor
public class MonitoredAppFacade {

    private final MonitoredAppService monitoredAppService;

    public ApplicationAdded add(AddApplication application) {
        MonitoredApp created = monitoredAppService.create(application);
        return created.dto();
    }
}
