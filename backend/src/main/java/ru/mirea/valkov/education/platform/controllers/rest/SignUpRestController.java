package ru.mirea.valkov.education.platform.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.valkov.education.platform.domain.services.SignService;
import ru.mirea.valkov.education.platform.dto.requests.AppUserDetailsRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/sign-up")
public class SignUpRestController {
    private final SignService signService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody AppUserDetailsRequest appUserDetailsRequest) {
        signService.registerUser(appUserDetailsRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/confirm")
    public ResponseEntity<?> confirmAnAccount(@RequestParam("token") String token) {
        signService.confirmAnAccount(token);
        return ResponseEntity.ok().build();
    }
}
