package health.hbp.controller;

import health.hbp.model.Preferences;
import health.hbp.repository.PreferencesRepository;
import health.hbp.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PreferencesControler {

    @Autowired
    PreferencesService service;

    @GetMapping("/preferences")
    public String getPreferences(Model model) {
        Preferences preferences = service.getPreferencesForLoggedUser(true);
        model.addAttribute("preferences", preferences);
        return "edit-preferences";
    }

    @PostMapping("/preferences/upsert")
    public String upsertPreferences(Preferences preferences) {
       service.savePreferencesForLoggedUser(preferences);
       return "redirect:/edibles";
    }
}
