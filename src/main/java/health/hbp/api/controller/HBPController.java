package health.hbp.api.controller;


import health.hbp.api.mapper.APIEdibleMapper;
import health.hbp.api.stub.iface.HbpApi;
import health.hbp.api.stub.model.Edible;
import health.hbp.repository.EdibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HBPController implements HbpApi {

    @Inject
    APIEdibleMapper mapper;

    @Autowired
    private EdibleRepository edibleRepository;

    @GetMapping("/hbp/api/v1.0/edibles")
    public ResponseEntity<List<Edible>> getAllEdibles() {
        List<Edible> edibles = new ArrayList<>();
        edibleRepository.findAll().forEach(edible -> edibles.add(mapper.map(edible)));
        return new ResponseEntity(edibles, HttpStatus.OK);
    }
}
