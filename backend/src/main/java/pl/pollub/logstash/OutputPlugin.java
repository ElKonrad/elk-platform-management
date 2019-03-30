package pl.pollub.logstash;

import org.springframework.stereotype.Component;

@Component
class OutputPlugin {
    String defaultConfig() {
        return "output {\n" +
                "    if [level] in [\"WARN\",\"ERROR\"] {\n" +
                "      stdout {\n" +
                "          codec => rubydebug\n" +
                "      }\n" +
                "\n" +
                "      elasticsearch {\n" +
                "          hosts => \"elasticsearch:9200\"\n" +
                "      }\n" +
                "    }\n" +
                "}\n";
    }
}
