package pl.pollub.logstash.filter;

import org.springframework.stereotype.Component;
import pl.pollub.monitoring.dto.ApplicationAdded;

import java.util.List;

@Component
public class FilterPluginBuilder {

    public String build(List<ApplicationAdded> applicationAddedList){
        StringBuilder filterPluginJson=new StringBuilder("filter {\n");
        applicationAddedList.forEach(ad->filterPluginJson.append(grokForApp(ad.getName(),ad.getGrokPattern())));
        filterPluginJson.append("}");
        return filterPluginJson.toString();
    }

    private String grokForApp(String appType,String grokPattern){
        return  "if [type] == \""+appType+"\"{\n"+
                "   grok {\n" +
                "      match => [ \"message\", \""+grokPattern+"\"]\n" +
                "    }\n" +
                "\n" +
                "    date {\n" +
                "      match => [ \"timestamp\" , \"yyyy-MM-dd HH:mm:ss.SSS\" ]\n" +
                "    }\n"+
                "}\n";
    }
}
