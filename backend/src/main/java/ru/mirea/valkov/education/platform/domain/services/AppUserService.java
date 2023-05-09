package ru.mirea.valkov.education.platform.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.repositories.AppUserRepository;
import ru.mirea.valkov.education.platform.email.EmailSender;
import ru.mirea.valkov.education.platform.domain.entities.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import ru.mirea.valkov.education.platform.email.EmailUtils;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    @Transactional
    public void createNewUser(AppUser appUser) throws IllegalStateException {
        Optional<AppUser> unexpectedUser = appUserRepository.findByUsername(appUser.getUsername());

        String token = UUID.randomUUID().toString();
        // TODO: remove hard code
        String linkToConfirmToken = "https://my-diary-appl.herokuapp.com/sign-up/confirm?token=" + token;

        ConfirmationToken confirmationToken = new ConfirmationToken(
                null,
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                null,
                null
        );

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        if (unexpectedUser.isEmpty()) {
            AppUser savedUser = appUserRepository.save(appUser);
            confirmationToken.setAppUser(savedUser);
        } else {
            if (unexpectedUser.get().isEnabled()) {
                throw new IllegalStateException(String.format("User with email %s already exists", appUser.getUsername()));
            } else {
                if (anyTokenNotExpired(unexpectedUser.get())) {
                    throw new IllegalStateException(String.format("User with email %s already exists", appUser.getUsername()));
                } else {
                    confirmationToken.setAppUser(unexpectedUser.get());
                }
            }
        }

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        emailSender.sendEmail(appUser.getUsername(), EmailUtils.buildEmail(linkToConfirmToken));
    }

    private boolean anyTokenNotExpired(AppUser notExpectedUser) {
        List<ConfirmationToken> confirmationTokens = confirmationTokenService.getTokensByUserId(notExpectedUser.getId());
        boolean anyTokenNotExpired = false;
        for (ConfirmationToken token: confirmationTokens) {
            if (token.getExpiresAt().isAfter(LocalDateTime.now())) {
                anyTokenNotExpired = true;
            }
        }
        return anyTokenNotExpired;
    }

    @Transactional
    public void confirmAnAccount(String token) throws IllegalStateException {
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationTokenByToken(token);

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token already expired");
        }

        AppUser appUser = appUserRepository.findById(confirmationToken.getAppUser().getId())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        confirmationTokenService.updateConfirmedAt(confirmationToken.getId(), LocalDateTime.now());

        appUserRepository.updateEnabledById(appUser.getId(), true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException(String.format("User with username %s not found", username)));
    }

    public AppUser saveUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

}
