package pl.pollub.logstash;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
class LogstashService {

    @Value("${logstash.sincedb.path.local.directory}")
    private String sinceDbPathDirectory;

    @Value("${logstash.configuration.filepath}")
    private String logstashConfigurationFilepath;

    @Value("${logstash.test.resource.log.file}")
    private String testLogFile;

    void injectSomeLogEntriesToLogstashLocalhostTCP() {
        try {
            Socket localhost = new Socket("localhost", 5000);
            Path pathToLogFile = Paths.get(getClass().getClassLoader()
                                                     .getResource(testLogFile)
                                                     .toURI());
            log.info("Inject some log entries from file " + pathToLogFile.toString());
            localhost.getOutputStream()
                     .write(Files.readAllBytes(pathToLogFile));
        } catch (IOException | URISyntaxException e) {
            log.error("Cannot inject some log entries", e);
        }
    }

    void persistConfig(String configuration) {
        try {
            FileUtils.writeStringToFile(new File(getClass().getClassLoader()
                                                           .getResource(logstashConfigurationFilepath)
                                                           .getFile()),
                                        configuration,
                                        "UTF-8");
        } catch (IOException e) {
            log.error("Cannot build logstash getConfiguration", e);
        }
    }

    void createLogstashSinceDbFiles(List<ApplicationAdded> apps) {
        for (ApplicationAdded app : apps) {
            try {
                FileUtils.touch(new File(sinceDbPathDirectory + app.getId()));
            } catch (IOException e) {
                log.error("Cannot create a logstash sinceDb file for app " + app.getName());
            }
        }
    }
}
