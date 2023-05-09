package ru.mirea.valkov.education.platform.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.AppUserRole;
import ru.mirea.valkov.education.platform.dto.requests.AppUserDetailsRequest;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SignService {
    private final AppUserService appUserService;

    public void registerUser(AppUserDetailsRequest request) throws IllegalStateException {
        AppUser appUser = new AppUser(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.STUDENT,
                true,
                true,
                true,
                false,
                Collections.emptySet()
        );

        appUserService.createNewUser(appUser);
    }

    public void confirmAnAccount(String token) throws IllegalStateException{
        appUserService.confirmAnAccount(token);
    }

}
