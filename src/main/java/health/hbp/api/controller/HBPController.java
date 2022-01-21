package health.hbp.api.controller;


import health.hbp.api.stub.iface.HbpApi;
import health.hbp.api.stub.model.Edible;
import health.hbp.repository.EdibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HBPController implements HbpApi {

    @Autowired
    private EdibleRepository edibleRepository;

    @GetMapping("/hbp/api/v1.0/edibles")
    public ResponseEntity<List<Edible>> getAllEdibles() {
        // TODO: Return swagger-generated Edible model
        return new ResponseEntity(edibleRepository.findAll(), HttpStatus.OK);
    }
}
