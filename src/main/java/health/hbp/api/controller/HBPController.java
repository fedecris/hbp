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
import java.util.Optional;

@Controller
public class HBPController implements HbpApi {

    @Inject
    APIEdibleMapper mapper;

    @Autowired
    private EdibleRepository edibleRepository;


    @Override
    public ResponseEntity<String> deleteEdible(String id) {
        edibleRepository.deleteById(id);
        return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
    }

    @GetMapping("/hbp/api/v1.0/edibles")
    public ResponseEntity<List<Edible>> getAllEdibles() {
        List<Edible> edibles = new ArrayList<>();
        edibleRepository.findAll().forEach(edible -> edibles.add(mapper.map(edible)));
        return new ResponseEntity(edibles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Edible> retrieveEdible(String id) {
        Optional edible = edibleRepository.findById(id);
        if (edible.isPresent()) {
            return new ResponseEntity<Edible>(mapper.map((health.hbp.model.Edible)edible.get()), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
