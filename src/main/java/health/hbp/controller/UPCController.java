package health.hbp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/upc")
public class UPCController {

    @Value("${upc.lookup.api.baseurl}")
    private String upcLookupURL;

    @GetMapping(value = "/lookup/{upc}")
    public ResponseEntity<String> upcLookup(@PathVariable("upc") String upc) {
        WebClient webClient = WebClient.create();
        Mono<String> resp = webClient.get()
                .uri(upcLookupURL + upc)
                .retrieve()
                .bodyToMono(String.class);
        resp.subscribe();
        return new ResponseEntity<>(resp.block(), HttpStatus.OK);
    }
}
