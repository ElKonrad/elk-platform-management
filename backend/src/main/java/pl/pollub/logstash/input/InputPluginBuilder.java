package pl.pollub.logstash.input;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.pollub.logstash.LogstashPlugin;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class InputPluginBuilder {

    @Value("${logstash.sincedb.path.docker.directory}")
    private String sinceDbPathDockerDirectory;

    public String build(List<ApplicationAdded> apps) {
        List<LogstashPlugin> inputPlugins = apps.stream()
                                               .map(app -> {
                                                   if(app.getInputType()==InputPluginType.FILE)
                                                        return new FileInputPlugin(app.getName(),
                                                           app.getFilePath(),
                                                           FileInputPlugin.CodecFileInputPlugin.defaultConfig(),
                                                           sinceDbPathDockerDirectory + app.getId().toString());
                                                   else
                                                       return new HttpInputPlugin(app.getName(),app.getPort());
                                               })
                                               .collect(toList());

        StringBuilder inputPluginJSON = init();
        for (LogstashPlugin app : inputPlugins) {
            inputPluginJSON.append(app.getConfiguration())
                       .append("\n");
        }
        end(inputPluginJSON);
        return inputPluginJSON.toString();
    }

    private static StringBuilder init() {
        return new StringBuilder("input {\n");
    }

    private static void end(StringBuilder inputPlugin) {
        inputPlugin.append("}");
    }
}