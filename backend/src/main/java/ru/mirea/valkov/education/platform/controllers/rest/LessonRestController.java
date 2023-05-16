package ru.mirea.valkov.education.platform.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.services.CourseService;
import ru.mirea.valkov.education.platform.domain.services.LessonService;
import ru.mirea.valkov.education.platform.dto.mappers.LessonInfoMapper;
import ru.mirea.valkov.education.platform.dto.requests.LessonDetailsRequest;
import ru.mirea.valkov.education.platform.dto.responses.LessonInfo;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class LessonRestController {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final LessonInfoMapper lessonInfoMapper;

    @PostMapping("/courses/{title}/lessons")
    public void createNewLesson(@PathVariable String title,
                                @RequestBody LessonDetailsRequest request,
                                UserDetails principal) {
        AppUser appUser = (AppUser) principal;
        Course course = courseService.getCourseByTitle(title);
        if (!course.getOwner().getUsername().equals(appUser.getUsername())) {
            throw new IllegalArgumentException();
        }
        lessonService.createNewLesson(course, request);
    }

    @GetMapping(value = "/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LessonInfo> getNextLessons(Authentication authentication) {
        return lessonService.findNextUsersLessons((AppUser) authentication.getPrincipal())
                .stream()
                .map(lessonInfoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
