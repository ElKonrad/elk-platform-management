package pl.pollub.logstash;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LogstashConfiguratorTest {

    private InputPlugin inputPlugin = new InputPlugin("/sincedb/");
    private FilterPlugin filterPlugin = new FilterPlugin();
    private OutputPlugin outputPlugin = new OutputPlugin();
    private LogstashConfigurator logstashConfigurator = new LogstashConfigurator(inputPlugin, filterPlugin, outputPlugin);

    @Test
    public void shouldBuildLogstashInputPlugin() throws IOException {
        //given
        ApplicationAdded app1 = new ApplicationAdded(1L, "app1", "/logs/app1.log");
        ApplicationAdded app2 = new ApplicationAdded(2L, "app2", "/logs/app2.log");
        List<ApplicationAdded> apps = Arrays.asList(app1, app2);

        //when
        String builtInputPlugin = inputPlugin.build(apps);

        //then
        assertEquals(fileContent("expectedInputPlugin"), builtInputPlugin);
    }


    @Test
    public void shouldBuildLogstashConfigurationFileWhenTwoAppsIsAdded() throws IOException {
        //given
        ApplicationAdded app1 = new ApplicationAdded(1L, "app1", "/logs/app1.log");
        ApplicationAdded app2 = new ApplicationAdded(2L, "app2", "/logs/app2.log");
        List<ApplicationAdded> apps = Arrays.asList(app1, app2);

        //when
        String logstashConfiguration = logstashConfigurator.createConfiguration(apps);

        //then
        assertEquals(fileContent("expectedLogstashConfigurationFile"), logstashConfiguration);
    }

    private String fileContent(String filename) throws IOException {
        return IOUtils.toString(getClass().getClassLoader()
                                          .getResourceAsStream(filename),
                                "UTF-8");
    }
}