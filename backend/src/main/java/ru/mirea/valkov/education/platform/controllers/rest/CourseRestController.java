package ru.mirea.valkov.education.platform.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.services.CourseService;
import ru.mirea.valkov.education.platform.dto.mappers.CourseInfoMapper;
import ru.mirea.valkov.education.platform.dto.responses.CourseInfo;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/courses")
@RequiredArgsConstructor
public class CourseRestController {
    private final CourseService courseService;
    private final CourseInfoMapper courseInfoMapper;

    @GetMapping("/{title}")
    public CourseInfo getCourse(@PathVariable String title) {
        Course course = courseService.getCourseByTitle(title);
        return courseInfoMapper.mapToDto(course);
    }

    @PostMapping("/{title}/subscribe")
    public void subscribeToCourse(@PathVariable String title, UserDetails principal) {
        courseService.subscribeUserToCourse(title, (AppUser) principal);
    }

    @PostMapping("/{title}/unsubscribe")
    public void unsubscribeFromCourse(@PathVariable String title, UserDetails principal) {
        courseService.unsubscribeUserFromCourse(title, (AppUser) principal);
    }

    @PostMapping
    public void addNewCourse(@RequestBody CourseInfo courseInfo, UserDetails principal) {
        AppUser appUser = (AppUser) principal;
        Course course = courseInfoMapper.mapToEntity(courseInfo, appUser);
        courseService.saveCourse(course);
    }

    @GetMapping("/subscriptions")
    public List<CourseInfo> subscriptions(UserDetails principal) {
        // TODO
        return List.of();
    }

    @GetMapping
    public List<CourseInfo> allCourses() {
        // TODO
        return List.of();
    }
}
