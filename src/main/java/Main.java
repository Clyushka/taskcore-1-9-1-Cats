import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static final String URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) {
        String jsonData = "";

        try (CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5_000)
                        .setSocketTimeout(30_000)
                        .setRedirectsEnabled(false)
                        .build())
                .build()) {

            HttpGet getRequest = new HttpGet(URI);
            CloseableHttpResponse getResponse = client.execute(getRequest);
            String statusLine = getResponse.getStatusLine().toString();

            //if status code 200 or 304
            if (statusLine.contains("200") || statusLine.contains("304")) {
                jsonData = new String(
                        getResponse.getEntity().getContent().readAllBytes(),
                        StandardCharsets.UTF_8);
            }

            //jackson
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            List<CatInfo> catInfoList = new ObjectMapper().readValue(jsonData, typeFactory.constructCollectionType(List.class, CatInfo.class));

            catInfoList.stream()
                    .filter(x -> x.getUpvotes() > 0)
                    .forEach(System.out::println);

            getResponse.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(0);
        }

    }
}
