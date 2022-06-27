package health.hbp.api.controller;


import health.hbp.api.mapper.APIEdibleMapper;
import health.hbp.api.stub.iface.HbpApi;
import health.hbp.api.stub.model.Edible;
import health.hbp.model.Facts;
import health.hbp.repository.EdibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

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
    public ResponseEntity<String> addEdible(Edible body) {
        health.hbp.model.Edible newEdible = new health.hbp.model.Edible();
        newEdible.setName(body.getName());
        newEdible.setBrand(body.getBrand());
        newEdible.setUpc(body.getUpc());
        if (body.getFacts()!=null) {
            Facts facts = new Facts();
            facts.setPortion(body.getFacts().getPortion());
            facts.setSodium(body.getFacts().getSodium());
            facts.setPotassium(body.getFacts().getPotassium());
            newEdible.setFacts(facts);
        }
        edibleRepository.save(newEdible);
        return new ResponseEntity<>(newEdible.getId(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEdible(String id) {
        edibleRepository.deleteById(id);
        return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
    }

    @Override
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

    @Override
    public ResponseEntity<String> updateEdible(Edible body, String id) {
        Optional edible = edibleRepository.findById(id);
        if (edible.isPresent()) {
            if (body.getName()!=null && body.getName().length()>0)
                ((health.hbp.model.Edible)edible.get()).setName(body.getName());
            if (body.getBrand()!=null && body.getBrand().length()>0)
                ((health.hbp.model.Edible)edible.get()).setBrand(body.getBrand());
            if (body.getUpc()!=null && body.getUpc().length()>0)
                ((health.hbp.model.Edible)edible.get()).setUpc(body.getUpc());
            if (body.getFacts()!=null && body.getFacts().getPortion()!=null && body.getFacts().getPortion().toString().length()>0)
                ((health.hbp.model.Edible)edible.get()).getFacts().setPortion(body.getFacts().getPortion());
            if (body.getFacts()!=null && body.getFacts().getSodium()!=null && body.getFacts().getSodium().toString().length()>0)
                ((health.hbp.model.Edible)edible.get()).getFacts().setSodium(body.getFacts().getSodium());
            if (body.getFacts()!=null && body.getFacts().getPotassium()!=null && body.getFacts().getPotassium().toString().length()>0)
                ((health.hbp.model.Edible)edible.get()).getFacts().setPotassium(body.getFacts().getPotassium());
            edibleRepository.save(((health.hbp.model.Edible)edible.get()));
            return new ResponseEntity(id, HttpStatus.OK);
        }
        return new ResponseEntity("No edible found with the specified ID", HttpStatus.NOT_FOUND);
    }

}
