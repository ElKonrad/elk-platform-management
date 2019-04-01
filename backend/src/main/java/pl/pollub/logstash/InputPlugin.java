package pl.pollub.logstash;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@NoArgsConstructor
@AllArgsConstructor
class InputPlugin {

    @Value("${logstash.sincedb.path.docker.directory}")
    private String sinceDbPathDockerDirectory;

    String build(List<ApplicationAdded> apps) {
        List<FileInputPlugin> filePlugin = apps.stream()
                                               .map(app -> new FileInputPlugin(app.getName(),
                                                                               app.getFilePath(),
                                                                               FileInputPlugin.CodecFileInputPlugin.defaultConfig(),
                                                                               sinceDbPathDockerDirectory + app.getId().toString()))
                                               .collect(toList());

        StringBuilder inputPlugin = init();
        for (FileInputPlugin app : filePlugin) {
            inputPlugin.append(app.configuration())
                       .append("\n");
        }
        end(inputPlugin);
        return inputPlugin.toString();
    }

    private static StringBuilder init() {
        return new StringBuilder("input {\n");
    }

    private static void end(StringBuilder inputPlugin) {
        inputPlugin.append("}");
    }
}

@RequiredArgsConstructor
class FileInputPlugin {
    private final String type;
    private final String path;
    private final CodecFileInputPlugin codec;
    private final String sinceDbPath;

    String configuration() {
        return "\tfile {\n" +
                "\t\ttype => \"" + type + "\"\n" +
                "\t\tpath => \"" + path + "\"\n" +
                "\t\tcodec => " + codec.configuration() + "\n" +
                "\t\tsincedb_path => \"" + sinceDbPath + "\"\n" +
                "\t}";
    }

    @RequiredArgsConstructor
    static class CodecFileInputPlugin {
        private final String pattern;
        private final String negate;
        private final String what;
        private final int autoFlushInterval;

        static CodecFileInputPlugin defaultConfig() {
            return new CodecFileInputPlugin("^%{TIMESTAMP_ISO8601:timestamp}",
                                            "true",
                                            "previous",
                                            1);
        }

        public String configuration() {
            return "multiline {\n" +
                    "\t\t\tpattern => \"" + pattern + "\"\n" +
                    "\t\t\tnegate => \"" + negate + "\"\n" +
                    "\t\t\twhat => \"" + what + "\"\n" +
                    "\t\t\tauto_flush_interval => " + autoFlushInterval + "\n" +
                    "\t\t\t}";
        }
    }

}