package pl.pollub.logstash;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.util.List;

@Component
@RequiredArgsConstructor
class LogstashConfigurator {

    private final InputPlugin inputPlugin;
    private final FilterPlugin filterPlugin;
    private final OutputPlugin outputPlugin;

    String createConfiguration(List<ApplicationAdded> apps) {
        String input = inputPlugin.build(apps);
        String filter = filterPlugin.defaultConfig(); //TODO change it later when filterPlugin will be implemented
        String output = outputPlugin.defaultConfig(); //TODO change it later when outputPlugin will be implemented

        return input +
                "\n" +
                filter +
                "\n" +
                output;
    }
}

