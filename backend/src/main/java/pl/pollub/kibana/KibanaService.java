package pl.pollub.kibana;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
class KibanaService {

    void createKibanaIndex() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:5601/api/saved_objects/index-pattern");
            String json = "{\"attributes\":{\"title\":\"logstash-*\",\"timeFieldName\":\"@timestamp\"}}";
            StringEntity stringEntity = new StringEntity(json);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("kbn-version", "6.6.0");
            client.execute(httpPost);
        } catch (IOException e) {
            log.error("Cannot create a Kibana index", e);
        }
    }
}
