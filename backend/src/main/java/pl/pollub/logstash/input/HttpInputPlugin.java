package pl.pollub.logstash.input;

import lombok.RequiredArgsConstructor;
import pl.pollub.logstash.LogstashPlugin;

@RequiredArgsConstructor
public class HttpInputPlugin implements LogstashPlugin {

    private final String type;
    private final int port;

    @Override
    public String getConfiguration() {
        return "  http {\n" +
                "    host => \"127.0.0.1\"\n" +
                "    port => "+port+"\n" +
                "    type => "+type+"\n"+
                "  }";
    }
}
