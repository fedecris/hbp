package health.hbp.api;

import health.hbp.security.JWTUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EdibleAPIIntegrationTest {

    protected final String basePath = "hbp/api/v1.0";

    protected final String ediblesEndPoint = "edibles";

    protected final String inexistentEdibleID = "0";

    protected String existentEdibleID = null;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected JWTUtils jwtUtils;

    @LocalServerPort
    protected int port;

    protected String getBaseURL() {
        return "http://127.0.0.1:" + port + "/" + basePath;
    }

    String token;

    protected HttpHeaders getHeaders() {
        if (token==null) {
            token = jwtUtils.buildToken("test");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        headers.set("Authorization", token);
        return headers;
    }

    @Test
    @BeforeAll
    public void insertNewEdibleShouldReturnOkAndEdibleID() {
        ResponseEntity<String> response =
                restTemplate.exchange(String.format("%s/%s", getBaseURL(), ediblesEndPoint),
                        HttpMethod.POST,
                        new HttpEntity<>(" { \"name\":\"Barra cereal XYZ\", \"brand\":\"FOOBAR\", \"facts\":{ \"portion\":100, \"sodium\":12 }  } ", getHeaders()),
                        String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
        assertThat(response.getBody().toString()).isNotEmpty();
        existentEdibleID = response.getBody().toString();
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
    public void editEdibleShouldReturnOKAndID() {
        ResponseEntity<String> response =
                restTemplate.exchange(String.format("%s/%s/%s", getBaseURL(), ediblesEndPoint, existentEdibleID),
                        HttpMethod.PUT,
                        new HttpEntity<>(" { \"name\":\"Barra Cereal QWERTY\" } ", getHeaders()),
                        String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
        assertThat(existentEdibleID).isEqualTo(response.getBody().toString());
    }

    @Test
    @AfterAll
    public void deleteExistentEdibleShouldReturnOK() {
        ResponseEntity<String> response =
                restTemplate.exchange(String.format("%s/%s/%s", getBaseURL(), ediblesEndPoint, existentEdibleID),
                        HttpMethod.DELETE,
                        new HttpEntity<>(getHeaders()),
                        String.class);
        assertThat(response.getStatusCode().toString()).contains("200");
    }
}
