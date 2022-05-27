package health.hbp.controller;

import health.hbp.model.Preferences;
import health.hbp.service.PreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller @RequiredArgsConstructor
public class PreferencesController {


    private final PreferencesService service;

    @GetMapping("/preferences")
    public String getPreferences(Model model) {
        Preferences preferences = service.getPreferencesForLoggedUser();
        model.addAttribute("preferences", preferences);
        return "edit-preferences";
    }

    @PostMapping("/preferences/upsert")
    public String upsertPreferences(Preferences preferences) {
       service.savePreferencesForLoggedUser(preferences);
       return "redirect:/edibles";
    }
}
