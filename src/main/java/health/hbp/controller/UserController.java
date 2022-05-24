package health.hbp.controller;

import health.hbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class UserController {

    @Autowired
    UserService repository;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(Model model, @RequestParam HashMap<String, String> formData) {
        String result = repository.register(formData.get("username"), formData.get("password"), formData.get("password2"));
        if (result != null) {
            model.addAttribute("errorMsg", result);
            return "register";
        }
        model.addAttribute("registerSuccess", 1);
        return "login";
    }



}
