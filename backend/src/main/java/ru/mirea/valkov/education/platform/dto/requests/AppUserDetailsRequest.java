package ru.mirea.valkov.education.platform.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDetailsRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
