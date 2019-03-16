package pl.pollub.kibana;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KibanaFacade {

    private final KibanaService kibanaService;

    public void createKibanaIndex() {
        kibanaService.createKibanaIndex();
    }
}
