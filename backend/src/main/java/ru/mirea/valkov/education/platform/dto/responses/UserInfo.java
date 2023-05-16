package ru.mirea.valkov.education.platform.dto.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private List<CourseInfo> subscriptions;
    private List<CourseInfo> ownCourses;
}
