package pl.pollub.logstash;

import org.springframework.stereotype.Component;

@Component
class FilterPlugin {
    String defaultConfig() {
        return "filter {\n" +
                "   grok {\n" +
                "      match => [ \"message\", \"(?m)%{TIMESTAMP_ISO8601:timestamp}%{SPACE}%{LOGLEVEL:level} %{NUMBER:pid} --- \\[(?<thread>[A-Za-z0-9-]+)\\] [A-Za-z0-9.]*\\.(?<class>[A-Za-z0-9#_]+)\\s*:\\s+(?<logmessage>.*)\"]\n" +
                "    }\n" +
                "\n" +
                "    date {\n" +
                "      match => [ \"timestamp\" , \"yyyy-MM-dd HH:mm:ss.SSS\" ]\n" +
                "    }\n" +
                "}";
    }
}
