package ru.mirea.valkov.education.platform.dto.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LessonInfo {
    private String courseTitle;
    private String theme;
    private String description;
    private String homework;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
}
