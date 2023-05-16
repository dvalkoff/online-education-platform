package ru.mirea.valkov.education.platform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.valkov.education.platform.domain.services.SignService;
import ru.mirea.valkov.education.platform.dto.requests.AppUserDetailsRequest;

//@Controller
@RequiredArgsConstructor
public class SignController {
    private final SignService signService;

    @GetMapping("/login")
    public String getLoginView() {
        return "registration/login";
    }

    @GetMapping("/sign-up")
    public String getRegisterView() {
        return "registration/sign-up";
    }

    @GetMapping("/sign-up/confirm")
    public String confirmAnAccount(@RequestParam("token") String token, Model model) {
        try {
            signService.confirmAnAccount(token);
            return "registration/account-activated";
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("sign-up")
    public String registerUser(Model model, AppUserDetailsRequest appUserDetailsRequest) {
        try {
            signService.registerUser(appUserDetailsRequest);
            model.addAttribute("name", appUserDetailsRequest.getFirstName());
            return "registration/confirm-email";
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "error-page";
        }

    }

}
