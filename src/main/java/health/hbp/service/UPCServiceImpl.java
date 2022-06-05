package health.hbp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service @Slf4j @Profile("prod")
public class UPCServiceImpl implements UPCService {

    @Value("${upc.lookup.api.baseurl}")
    private String upcLookupURL;

    @Cacheable("upcs")
    public String upcLookup (String upc) throws ResponseStatusException {
        log.info(String.format("Looking up %s... " , upc));
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri(upcLookupURL + upc)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorMap(err -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UPC info not found"))
                .block();
    }
}
