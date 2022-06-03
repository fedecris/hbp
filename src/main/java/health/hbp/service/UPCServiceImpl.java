package health.hbp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service @Slf4j
public class UPCServiceImpl implements UPCService {

    @Value("${upc.lookup.api.baseurl}")
    private String upcLookupURL;

    @Cacheable("upcs")
    public String upcLookup (String upc) {
        log.info(String.format("Looking up %s... " , upc));
        WebClient webClient = WebClient.create();
        Mono<String> resp = webClient.get()
                .uri(upcLookupURL + upc)
                .retrieve()
                .bodyToMono(String.class);
        resp.subscribe();
        return resp.block();
    }
}
