package pl.pollub.monitoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.monitoring.dto.AddApplication;

@Service
@RequiredArgsConstructor
class MonitoredAppServiceImpl implements MonitoredAppService {

    private final MonitoredAppRepository monitoredAppRepository;

    @Override
    public MonitoredApp create(AddApplication application) {
        MonitoredApp monitoredApp = MonitoredAppCreator.from(application);
        monitoredApp.created();
        return monitoredAppRepository.save(monitoredApp);
    }
}
