package pl.pollub.monitoring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pollub.logstash.input.InputPluginType;

@RequiredArgsConstructor
@Getter
public class ApplicationAdded {

    private final Long id;
    private final String name;
    private final String grok;
    private final String filePath;
    private final InputPluginType inputType;
    private final int port;
}
