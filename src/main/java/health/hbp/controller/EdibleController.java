package health.hbp.controller;

import health.hbp.dto.EdibleDTO;
import health.hbp.mapper.EdibleMapper;
import health.hbp.model.Edible;
import health.hbp.repository.EdibleRepository;
import health.hbp.service.EdibleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/edibles")
@RequiredArgsConstructor
public class EdibleController {

    private final EdibleRepository repository;

    private final EdibleService edibleService;

    @Inject
    private EdibleMapper mapper;

    @GetMapping("")
    public String getAllEdibles(Model model,
                                @RequestParam(value = "sort", defaultValue = "name", required=false) String sort,
                                @RequestParam(value = "dir",  defaultValue = "asc",  required=false) String dir,
                                @RequestParam(value = "page",  defaultValue = "0",  required=false) Integer page,
                                @RequestParam(value = "limit",  defaultValue = "10",  required=false) Integer limit) {
        Pageable pageReq = PageRequest.of(page, limit, Sort.by("asc".equals(dir)?Sort.Direction.ASC:Sort.Direction.DESC, sort));
        Page<Edible> ediblePage = repository.findAll(pageReq);
        List<Edible> edibles = ediblePage.get().collect(Collectors.toList());
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        if (ediblePage.hasPrevious())
            model.addAttribute("prevPage", page-1);
        if (ediblePage.hasNext())
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("currPage", ediblePage.getNumber()+1);
        model.addAttribute("firstPage", 0);
        model.addAttribute("lastPage", ediblePage.getTotalPages()-1);
        model.addAttribute("totalPages", ediblePage.getTotalPages());
        model.addAttribute("edibles", edibleToDTO(edibles));
        return "list-edibles";
    }

    @GetMapping({"/edit", "/edit/{id}"})
    public String addEdible(Model model, @PathVariable("id") Optional<String> id) {
        if (id.isPresent()) {
            repository.findById(id.get()).ifPresent(edible -> model.addAttribute("edible", edible));
        } else {
            model.addAttribute("edible", new Edible());
        }
        return "edit-edibles";
    }

    @GetMapping("/view/{id}")
    public String viewEdible(Model model, @PathVariable("id") Optional<String> id) {
        if (id.isPresent() && repository.findById(id.get()).isPresent()) {
            model.addAttribute("edible", mapper.edibleToEdibleDTO(repository.findById(id.get()).get()));
            return "view-edibles";
        }
        return "error-404";
    }

    @GetMapping("/delete/{id}")
    public String deleteEdible(@PathVariable("id") String id) {
        repository.deleteById(id);
        return "redirect:/edibles";
    }

    @PostMapping("/upsert")
    public String upsertEdible(Edible edible) {
        if (edible.getId()==null || edible.getId().length()==0) {
            edible.setId(null);
            repository.insert(edible);
        } else {
            repository.save(edible);
        }
        return "redirect:/edibles";
    }

    @GetMapping("/find/name/{criteria}")
    public String findEdiblesByName(Model model,
                                   @PathVariable(value = "criteria", required = true) String criteria) {
        List<Edible> edibles = repository.findByNameLikeIgnoreCase(criteria, Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("edibles", edibleToDTO(edibles));
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    @GetMapping("/find/brand/{criteria}")
    public String findEdiblesByBrand(Model model,
                                    @PathVariable(value = "criteria", required = true) String criteria) {
        List<Edible> edibles = repository.findByBrandLikeIgnoreCase(criteria, Sort.by(Sort.Direction.ASC, "brand"));
        model.addAttribute("edibles", edibleToDTO(edibles));
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    @GetMapping("/find/upc/{criteria}")
    public String findEdiblesByUpc(Model model,
                                    @PathVariable(value = "criteria", required = true) String criteria) {
        List<Edible> edibles = repository.findByUpcLike(criteria);
        model.addAttribute("edibles", edibleToDTO(edibles));
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    @GetMapping("/filter/sodium/under/{percent}")
    public String getEdiblesUnderGivenSodiumSaturationPercent(Model model,
                         @PathVariable(value = "percent", required = true) Float percent) {
        model.addAttribute("edibles", edibleService.getEdiblesUnderGivenSodiumSaturationPercent(percent));
        model.addAttribute("findMode", 1);
        return "list-edibles";
    }

    @GetMapping("/filter/sodium/less/than/potassium")
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
