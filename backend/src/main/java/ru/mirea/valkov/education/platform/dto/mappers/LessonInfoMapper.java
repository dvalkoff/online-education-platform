package ru.mirea.valkov.education.platform.dto.mappers;

import org.springframework.stereotype.Service;
import ru.mirea.valkov.education.platform.domain.entities.Lesson;
import ru.mirea.valkov.education.platform.dto.responses.LessonInfo;

@Service
public class LessonInfoMapper {
    public LessonInfo mapToDto(Lesson lesson) {
        return LessonInfo.builder()
                .courseTitle(lesson.getCourse().getTitle())
                .theme(lesson.getTheme())
                .description(lesson.getDescription())
                .homework(lesson.getHomework())
                .startsAt(lesson.getStartsAt())
                .endsAt(lesson.getEndsAt())
                .build();
    }
}
