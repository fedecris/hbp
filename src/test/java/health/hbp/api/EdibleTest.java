package health.hbp.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EdibleTest {

    protected final String basePath = "hbp/api/v1.0";

    protected final String ediblesEndPoint = "edibles";

    protected final String inexistentEdibleID = "0";

    protected final String existentEdibleID = "61ddbe22aa118b68ca5c926e";

    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    protected int port;

    protected String getBaseURL() {
        return "http://localhost:" + port + "/" + basePath;
    }

    @Test
    public void retrieveInexistentEdibleShouldReturnNotFound() {
        ResponseEntity<String> response =
                restTemplate.exchange(String.format("%s/%s/%s", getBaseURL(), ediblesEndPoint, inexistentEdibleID),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        String.class);
        assertThat(response.getStatusCode().toString()).contains("404");
    }

    @Test
    public void retrieveExistentEdibleShouldReturnOK() {
        ResponseEntity<String> response =
                restTemplate.exchange(String.format("%s/%s/%s", getBaseURL(), ediblesEndPoint, existentEdibleID),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
    }

    @Test
    public void postEdibleChangeShouldReturnOKAndID() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        ResponseEntity<String> response =
                restTemplate.exchange(String.format("%s/%s/%s", getBaseURL(), ediblesEndPoint, existentEdibleID),
                        HttpMethod.PUT,
                        new HttpEntity<>(" { \"name\":\"Barra Cereal Frutillita\" } ", headers),
                        String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
        assertThat(existentEdibleID).isEqualTo(response.getBody().toString());
    }
}
