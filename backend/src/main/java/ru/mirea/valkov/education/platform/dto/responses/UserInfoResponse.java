package ru.mirea.valkov.education.platform.dto.responses;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserInfoResponse {
    private String firstName;
    private String lastName;
    private String username;
    private Set<CourseInfoResponse> subscriptions;
}
