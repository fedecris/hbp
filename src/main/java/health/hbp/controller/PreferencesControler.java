package health.hbp.controller;

import health.hbp.model.Preferences;
import health.hbp.repository.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PreferencesControler {

    @Autowired
    PreferencesRepository repository;

    @GetMapping("/preferences")
    public String getPreferences(Model model) {
        Preferences preferences = (repository.count() > 0 ? repository.findAll().get(0) : new Preferences("1"));
        model.addAttribute("preferences", preferences);
        return "edit-preferences";
    }

    @PostMapping("/preferences/upsert")
    public String upsertPreferences(Preferences preferences) {
       repository.save(preferences);
       return "redirect:/edibles";
    }
}
