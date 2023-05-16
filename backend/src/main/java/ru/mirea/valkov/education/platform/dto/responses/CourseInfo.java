package ru.mirea.valkov.education.platform.dto.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseInfo {
    private Long id;
    private String title;
    private String description;
    private String ownerUsername;
}
