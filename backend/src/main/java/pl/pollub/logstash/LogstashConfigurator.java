package pl.pollub.logstash;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.logstash.filter.FilterPluginBuilder;
import pl.pollub.logstash.input.InputPluginBuilder;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.util.List;

@Component
@RequiredArgsConstructor
class LogstashConfigurator {

    private final InputPluginBuilder inputPluginBuilder;
    private final FilterPluginBuilder filterPluginBuilder;
    private final OutputPlugin outputPlugin;

    String createConfiguration(List<ApplicationAdded> apps) {
        String input = inputPluginBuilder.build(apps);
        String filter = filterPluginBuilder.build(apps);
        String output = outputPlugin.defaultConfig();

        return input +
                "\n" +
                filter +
                "\n" +
                output;
    }
}

