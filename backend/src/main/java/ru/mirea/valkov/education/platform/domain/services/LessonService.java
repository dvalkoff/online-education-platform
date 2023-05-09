package ru.mirea.valkov.education.platform.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.entities.Lesson;
import ru.mirea.valkov.education.platform.domain.repositories.LessonRepository;
import ru.mirea.valkov.education.platform.dto.requests.LessonDetailsRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> findUsersLessons(AppUser appUser) {
        return lessonRepository.findAllByCourse_SubscribersOrderByStartsAt(appUser);
    }

    public List<Lesson> getLessonsByCourse(Course course) {
        return lessonRepository.findAllByCourse(course);
    }

    public void createNewLesson(Course course, LessonDetailsRequest request) {
        Lesson lesson = new Lesson(
                course,
                request.getTheme(),
                request.getDescription(),
                request.getHomework(),
                LocalDateTime.parse(request.getStartsAt()),
                LocalDateTime.parse(request.getEndsAt())
        );

        lessonRepository.save(lesson);
    }
}
