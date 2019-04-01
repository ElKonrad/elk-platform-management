package pl.pollub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.pollub.monitoring.MonitoredAppFacade;
import pl.pollub.monitoring.dto.AddApplication;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.util.List;

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

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ApplicationAdded>> getApplications() {
        return new ResponseEntity<>(monitoredAppFacade.getAllApps(), HttpStatus.OK);
    }
}
