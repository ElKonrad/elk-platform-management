package pl.pollub.logstash.input;

import lombok.RequiredArgsConstructor;
import pl.pollub.logstash.LogstashPlugin;

@RequiredArgsConstructor
    class FileInputPlugin implements LogstashPlugin {
        private final String type;
        private final String path;
        private final CodecFileInputPlugin codec;
        private final String sinceDbPath;

        public String getConfiguration() {
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

