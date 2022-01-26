package health.hbp.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EdibleTest {

    protected final String baseURL = "/hbp/api/v1.0";

    protected final String ediblesEndPoint = "edibles";

    protected final String inexistentEdibleID = "0";

    protected final String existentEdibleID = "61ddbe22aa118b68ca5c926e";

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void retrieveInexistentEdibleShouldReturnNotFound() {
        ResponseEntity<String> response =
                restTemplate.exchange(String.format("/%s/%s/%s", baseURL, ediblesEndPoint, inexistentEdibleID),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        String.class);
        assertThat(response.getStatusCode().toString()).contains("404");
    }

    @Test
    public void retrieveExistentEdibleShouldReturnOK() {
        ResponseEntity<String> response =
                restTemplate.exchange(String.format("/%s/%s/%s", baseURL, ediblesEndPoint, existentEdibleID),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
    }
}
