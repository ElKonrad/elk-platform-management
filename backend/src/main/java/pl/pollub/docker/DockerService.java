package pl.pollub.docker;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
class DockerService {

    private final AtomicBoolean started = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(true);
    private final AtomicBoolean shouldStop = new AtomicBoolean(false);

    void up() {
        try {
            log.info("Request to starting ELK docker-compose...");

            if (started.get() == false && stopped.get()) {
                started.set(true);
                stopped.set(false);
                shouldStop.set(false);

                CommandLine commandLine = new CommandLine("docker-compose");
                commandLine.addArgument("up");
                DefaultExecutor defaultExecutor = prepareDockerComposeExecutor();
                defaultExecutor.execute(commandLine);
            } else
                throw new IllegalArgumentException("ELK docker-compose already up and running");
        } catch (ExecuteException e) {
            log.info("Stopping ELK docker-compose...");
            shouldStop.set(true);
            stop();
        } catch (IOException | URISyntaxException e) {
            log.error("Cannot start a ELK docker-compose service", e);
        }
    }

    void stop() {
        try {
            if (started.get() || shouldStop.get()) {
                log.info("Request to stopping ELK docker-compose...");
                started.set(false);
                stopped.set(true);
                CommandLine commandLine = new CommandLine("docker-compose");
                commandLine.addArgument("stop");
                DefaultExecutor defaultExecutor = prepareDockerComposeExecutor();
                defaultExecutor.execute(commandLine);
            } else
                throw new IllegalArgumentException("ELK docker-compose is stopped now");
        } catch (ExecuteException e) {
            log.info("Stopped ELK docker-compose");
        } catch (IOException | URISyntaxException e) {
            log.error("Cannot stop a ELK docker-compose service", e);
        }
    }

    void restart() {
        try {
            if (started.get() && stopped.get() == false && shouldStop.get() == false) {
                log.info("Request to restarting ELK docker-compose...");
                CommandLine commandLine = new CommandLine("docker-compose");
                commandLine.addArgument("restart");
                DefaultExecutor defaultExecutor = prepareDockerComposeExecutor();
                started.set(true);
                stopped.set(false);
                shouldStop.set(false);
                defaultExecutor.execute(commandLine);
            } else
                throw new IllegalArgumentException("ELK docker-compose has been just stopped");
        } catch (IOException | URISyntaxException e) {
            log.error("Cannot restart a ELK docker-compose service", e);
        }
    }

    private DefaultExecutor prepareDockerComposeExecutor() throws URISyntaxException {
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        File dockerComposeDirectory = Paths.get(getClass().getClassLoader()
                                                          .getResource("docker-elk")
                                                          .toURI())
                                           .toFile();
        defaultExecutor.setWorkingDirectory(dockerComposeDirectory);
        return defaultExecutor;
    }
}
