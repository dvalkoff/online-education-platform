package ru.mirea.valkov.education.platform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.services.CourseService;
import ru.mirea.valkov.education.platform.domain.services.IndexService;
import ru.mirea.valkov.education.platform.domain.services.LessonService;
import ru.mirea.valkov.education.platform.dto.requests.LessonDetailsRequest;

@Controller
@RequiredArgsConstructor
public class LessonController {
    private final IndexService indexService;
    private final CourseService courseService;
    private final LessonService lessonService;

    @GetMapping("/courses/{courseTitle}/create-lesson")
    public String getNewLessonView(@PathVariable("courseTitle") String courseTitle, Model model) {

        Course course = indexService.getCourseByTitle(courseTitle);
        model.addAttribute("course", course);
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (course.getOwner().getUsername().equals(user.getUsername())) {
            return "lesson/create-lesson";
        } else {
            model.addAttribute("error", "access denied");
            return "error-page";
        }
    }

    @PostMapping("/courses/{courseTitle}/create-lesson")
    public String createNewLesson(@PathVariable("courseTitle") String courseTitle, LessonDetailsRequest request) {
        Course course = indexService.getCourseByTitle(courseTitle);
        lessonService.createNewLesson(course, request);
        return "redirect:/courses/" + courseTitle;
    }
}
