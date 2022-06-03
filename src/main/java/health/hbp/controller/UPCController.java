package health.hbp.controller;


import health.hbp.service.UPCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/upc")
@RequiredArgsConstructor
public class UPCController {

    private final UPCService upcService;

    @GetMapping(value = "/lookup/{upc}")
    public ResponseEntity<String> upcLookup(@PathVariable("upc") String upc) {
        return new ResponseEntity<>(upcService.upcLookup(upc), HttpStatus.OK);
    }
}
