package pl.pollub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.monitoring.MonitoredAppFacade;
import pl.pollub.monitoring.dto.AddApplication;
import pl.pollub.monitoring.dto.ApplicationAdded;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
class MonitoredAppController {

    private final MonitoredAppFacade monitoredAppFacade;

    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApplicationAdded> addApplication(@RequestBody AddApplication application) {
        ApplicationAdded added = monitoredAppFacade.addAppAndRebuildConfig(application);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }
}
