package pl.pollub.logstash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
class LogstashService {

    void injectSomeLogEntriesToLogstashLocalhostTCP() {
        try {
            Socket localhost = new Socket("localhost", 5000);
            Path pathToLogFile = Paths.get(getClass().getClassLoader()
                                                     .getResource("sample_logs.log")
                                                     .toURI());
            log.info("Inject some log entries from file " + pathToLogFile.toString());
            localhost.getOutputStream()
                     .write(Files.readAllBytes(pathToLogFile));
        } catch (IOException | URISyntaxException e) {
            log.error("Cannot inject some log entries", e);
        }
    }
}
