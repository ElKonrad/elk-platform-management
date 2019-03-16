package pl.pollub.monitoring;

import pl.pollub.monitoring.dto.AddApplication;

interface MonitoredAppService {
    MonitoredApp create(AddApplication application);
}
