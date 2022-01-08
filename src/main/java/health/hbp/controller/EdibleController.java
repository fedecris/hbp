package health.hbp.controller;

import health.hbp.model.Edible;
import health.hbp.repository.EdibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EdibleController {

    @Autowired
    private EdibleRepository repository;

    @GetMapping("/edibles")
    public String getAllEdibles(Model model) {
        model.addAttribute("edibles", repository.findAll());
        return "list-edibles";
    }

    @GetMapping({"/edibles/edit", "/edibles/edit/{id}"})
    public String addEdible(Model model, @PathVariable("id") Optional<String> id) {
        if (id.isPresent()) {
            repository.findById(id.get()).ifPresent(edible -> model.addAttribute("edible", edible));
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
