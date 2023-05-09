package ru.mirea.valkov.education.platform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.entities.Lesson;
import ru.mirea.valkov.education.platform.domain.services.IndexService;
import ru.mirea.valkov.education.platform.dto.requests.CourseDetailsRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping(
        path = "/courses"
)
@RequiredArgsConstructor
public class CourseController {
    private final IndexService indexService;

    @GetMapping
    public String getAllCoursesView(@RequestParam(value = "page", required = false) Integer page, Model model) {
        try {
            if (page == null) page = 1;
            page--;
            PageRequest pageable = PageRequest.of(page, 5);

            Page<Course> paging = indexService.getCoursesPageable(pageable);
            List<Integer> pages = IntStream.rangeClosed(1, paging.getTotalPages()).boxed().collect(Collectors.toList());
            AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            model.addAttribute("user", user);
            model.addAttribute("courses", paging);
            model.addAttribute("pages", pages);
            model.addAttribute("subscriptions", indexService.getUserSubscriptions());

            return "course/courses";

        } catch (ClassCastException e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }

    }

    @GetMapping("/{courseTitle}")
    public String getCourse(@PathVariable("courseTitle") String courseTitle, Model model) {
        Course course = indexService.getCourseByTitle(courseTitle);
        model.addAttribute("course", course);

        List<Lesson> lessons = indexService.getLessonsByCourse(course);
        model.addAttribute("lessons", lessons);
        model.addAttribute("formatter", "%02d");

        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);

        return "course/course";
    }


    @GetMapping("/{courseTitle}/subscribe")
    public String subscribeToCourse(@PathVariable("courseTitle") String courseTitle, Model model) {
        try {
            indexService.subscribeUserToCourse(courseTitle);
            return "redirect:/courses";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/{courseTitle}/unsubscribe")
    public String unsubscribeFromCourse(@PathVariable("courseTitle") String courseTitle, Model model) {
        try {
            indexService.unsubscribeUserFromCourse(courseTitle);
            return "redirect:/courses";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/create")
    public String getCourseCreationView() {
        return "course/create-course";
    }

    @PostMapping("/create")
    public String createNewCourse(CourseDetailsRequest courseDetailsRequest) {
        indexService.createNewCourse(courseDetailsRequest);
        return "redirect:/courses";
    }
}
