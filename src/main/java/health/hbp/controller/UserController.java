package health.hbp.controller;

import health.hbp.security.JWTUtils;
import health.hbp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Optional;

@Controller @RequiredArgsConstructor
public class UserController {

    private final UserService service;

    private final JWTUtils jwtUtils;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(Model model, @RequestParam HashMap<String, String> formData) {
        Optional<String> registerErr = service.register(formData.get("username"), formData.get("password"), formData.get("password2"));
        if (registerErr.isPresent()) {
            model.addAttribute("errorMsg", registerErr.get());
            return "register";
        }
        model.addAttribute("registerSuccess", 1);
        return "login";
    }

    @GetMapping("/token")
    public String keygen(Model model) {
        model.addAttribute("tokenInfo", jwtUtils.buildToken());
        return "get-token";
    }


}
