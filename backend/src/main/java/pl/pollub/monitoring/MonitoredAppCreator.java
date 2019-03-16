package pl.pollub.monitoring;

import pl.pollub.monitoring.dto.AddApplication;

class MonitoredAppCreator {

    public static MonitoredApp from(AddApplication addApplication) {
        return MonitoredApp.builder()
                           .name(addApplication.getName())
                           .build();
    }
}
