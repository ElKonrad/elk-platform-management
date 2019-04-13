package pl.pollub.monitoring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.pollub.logstash.input.InputPluginType;

@RequiredArgsConstructor
@Getter
public class AddApplication {

    private final String name;
    private final String grokPattern;
    private final String filePath;
    private final InputPluginType inputType;
    private final int httpPort;
}
