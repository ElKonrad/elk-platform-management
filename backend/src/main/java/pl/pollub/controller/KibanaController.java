package pl.pollub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.kibana.KibanaFacade;
import pl.pollub.monitoring.dto.ApplicationAdded;

@RestController
@RequestMapping("/api/dev/kibana")
@RequiredArgsConstructor
class KibanaController {


    private final KibanaFacade kibanaFacade;

    @GetMapping(value = "index")
    public ResponseEntity<ApplicationAdded> createKibanaIndex() {
        kibanaFacade.createKibanaIndex();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
