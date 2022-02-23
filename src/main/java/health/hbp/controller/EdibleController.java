package health.hbp.controller;

import health.hbp.dto.EdibleDTO;
import health.hbp.mapper.EdibleMapper;
import health.hbp.model.Edible;
import health.hbp.repository.EdibleRepository;
import health.hbp.service.EdibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private EdibleService edibleService;

    @GetMapping("/edibles")
    public String getAllEdibles(Model model,
                                @RequestParam(value = "sort", defaultValue = "name", required=false) String sort,
                                @RequestParam(value = "dir",  defaultValue = "asc",  required=false) String dir ) {
        List<Edible> edibles = repository.findAll(Sort.by("asc".equals(dir)?Sort.Direction.ASC:Sort.Direction.DESC, sort));
        model.addAttribute("edibles", edibleToDTO(edibles));
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

    @GetMapping("/edibles/view/{id}")
    public String viewEdible(Model model, @PathVariable("id") Optional<String> id) {
        if (id.isPresent() && repository.findById(id.get()).isPresent()) {
            model.addAttribute("edible", mapper.edibleToEdibleDTO(repository.findById(id.get()).get()));
            return "view-edibles";
        }
        return "error-404";
    }

    @GetMapping("/edibles/delete/{id}")
    public String deleteEdible(@PathVariable("id") String id) {
        repository.deleteById(id);
        return "redirect:/edibles";
    }

    @PostMapping("/edibles/upsert")
    public String upsertEdible(Edible edible) {
        if (edible.getId()==null || edible.getId().length()==0) {
            edible.setId(null);
            repository.insert(edible);
        } else {
            repository.save(edible);
        }
        return "redirect:/edibles";
    }

    @GetMapping("/edibles/find/name/{criteria}")
    public String findEdiblesByName(Model model,
                                   @PathVariable(value = "criteria", required = true) String criteria) {
        List<Edible> edibles = repository.findByNameLike(criteria, Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("edibles", edibleToDTO(edibles));
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    @GetMapping("/edibles/find/brand/{criteria}")
    public String findEdiblesByBrand(Model model,
                                    @PathVariable(value = "criteria", required = true) String criteria) {
        List<Edible> edibles = repository.findByBrandLike(criteria, Sort.by(Sort.Direction.ASC, "brand"));
        model.addAttribute("edibles", edibleToDTO(edibles));
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    @GetMapping("/edibles/find/upc/{criteria}")
    public String findEdiblesByUpc(Model model,
                                    @PathVariable(value = "criteria", required = true) String criteria) {
        List<Edible> edibles = repository.findByUpc(criteria);
        model.addAttribute("edibles", edibleToDTO(edibles));
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    @GetMapping("/edibles/filter/sodium/under/{percent}")
    public String getEdiblesUnderGivenSodiumSaturationPercent(Model model,
                         @PathVariable(value = "percent", required = true) Float percent) {
        model.addAttribute("edibles", edibleService.getEdiblesUnderGivenSodiumSaturationPercent(percent));
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    @GetMapping("/edibles/filter/sodium/less/than/potassium")
    public String getEdiblesWithLessSodiumThanPotassium(Model model) {
        model.addAttribute("edibles", edibleService.getEdiblesWithLessSodiumThanPotassium());
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    protected List<EdibleDTO> edibleToDTO(List<Edible> edibles) {
        List<EdibleDTO> edibleDTOs = new ArrayList<>();
        edibles.forEach(edible -> edibleDTOs.add(mapper.edibleToEdibleDTO(edible)));
        return edibleDTOs;
    }



}
