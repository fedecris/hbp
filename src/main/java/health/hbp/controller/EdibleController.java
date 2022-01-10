package health.hbp.controller;

import health.hbp.dto.EdibleDTO;
import health.hbp.mapper.EdibleMapper;
import health.hbp.model.Edible;
import health.hbp.repository.EdibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EdibleController {

    @Autowired
    private EdibleRepository repository;

    @Inject
    private EdibleMapper mapper;

    @GetMapping("/edibles")
    public String getAllEdibles(Model model) {
        List<Edible> edibles = repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<EdibleDTO> edibleDTOs = new ArrayList<>();
        edibles.forEach(edible -> edibleDTOs.add(mapper.edibleToEdibleDTO(edible)));
        model.addAttribute("edibles", edibleDTOs);
        return "list-edibles";
    }

    @GetMapping({"/edibles/edit", "/edibles/edit/{id}"})
    public String addEdible(Model model, @PathVariable("id") Optional<String> id) {
        if (id.isPresent()) {
            repository.findById(id.get()).ifPresent(
                    edible -> {
                        model.addAttribute("edible", edible);
                    });
        } else {
            model.addAttribute("edible", new Edible());
        }
        return "edit-edibles";
    }

    @GetMapping("/edibles/delete/{id}")
    public String deleteEdible(@PathVariable("id") String id) {
        repository.deleteById(id);
        return "redirect:/edibles";
    }

    @PostMapping("/edibles/upsert")
    public String upsertEdible(Edible edible) {
        repository.upsert(edible);
        return "redirect:/edibles";
    }
}
