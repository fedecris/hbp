package health.hbp.controller;

import health.hbp.security.JWTUtils;
import health.hbp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @GetMapping("/login/oauth2")
    public String oauth2login() {
        return "oauth-login";
    }

    @GetMapping("/login/oauth2/code/google")
    public String oauth2() {
        return "redirect:/edibles";
    }

    @GetMapping("/oauth2google")
    public String oauth2google(Model model, @RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient, @AuthenticationPrincipal OAuth2User oAuth2User) {
        regOA2Info(model, authorizedClient, oAuth2User);
        return "oauth";
    }

    @GetMapping("/oauth2github")
    public String oauth2github(Model model, @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient, @AuthenticationPrincipal OAuth2User oAuth2User) {
        regOA2Info(model, authorizedClient, oAuth2User);
        return "oauth";
    }

    protected void regOA2Info(Model model, OAuth2AuthorizedClient authorizedClient, @AuthenticationPrincipal OAuth2User oAuth2User) {
        String dummy = ""+System.currentTimeMillis();
        service.register(oAuth2User.getName(), dummy, dummy);
        model.addAttribute("userName", oAuth2User.getName() );
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName() );
        model.addAttribute("userAttribute", oAuth2User.getAttributes() );
    }

}
