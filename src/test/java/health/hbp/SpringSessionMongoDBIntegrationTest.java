package health.hbp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.session.data.mongo.MongoIndexedSessionRepository;
import org.springframework.session.data.mongo.MongoSession;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringSessionMongoDBIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MongoIndexedSessionRepository repository;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private String cookie = null;

    @Test
    public void callingSessionCheckSeveralTimesWithSameCookieShouldMatchExpectedValue() {
        for (int i=0; i<5; i++) {
            Pair result = callCheckOnce();
            System.out.println(result);
            assertThat(result.getFirst()).isEqualTo(result.getSecond());
        }
    }

    private Pair<String, String> callCheckOnce() {
        HttpHeaders headers = createHeaders();
        HttpEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/session/check", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        saveCookie(response);
        MongoSession session = repository.findById(getSessionId(cookie));
        return Pair.of(response.getBody(), session.getAttribute("count").toString());
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        if (cookie!=null) {
            headers.add("Cookie", cookie);
        }
        return headers;
    }

    private void saveCookie(HttpEntity<String> response) {
        if (cookie==null) {
            cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        }
    }

    private String getSessionId(String cookie) {
        return new String(Base64.getDecoder().decode(cookie.split(";")[0].split("=")[1]));
    }

}