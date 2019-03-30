package pl.pollub.monitoring;

import pl.pollub.monitoring.dto.AddApplication;

import java.util.List;

interface MonitoredAppService {
    MonitoredApp create(AddApplication application);

    List<MonitoredApp> getAll();
}
