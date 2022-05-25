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
import java.util.Optional;

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
        Optional<String> registerErr = repository.register(formData.get("username"), formData.get("password"), formData.get("password2"));
        if (registerErr.isPresent()) {
            model.addAttribute("errorMsg", registerErr.get());
            return "register";
        }
        model.addAttribute("registerSuccess", 1);
        return "login";
    }



}
