package ru.mirea.valkov.education.platform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.services.IndexService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    @GetMapping
    public String getIndexView(Model model) {
        try {
            model.addAttribute("lessons", indexService.getUsersLesson());
            model.addAttribute("formatter", "%02d");
            return "index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/subscriptions")
    public String getSubscriptionsView(@RequestParam(name = "page", required = false) Integer page, Model model) {
        try {
            if (page == null) page = 1;
            page--;
            PageRequest pageable = PageRequest.of(page, 5);

            Page<Course> paging = indexService.getUserSubscriptionsPageable(pageable);
            List<Integer> pages = IntStream.rangeClosed(1, paging.getTotalPages()).boxed().collect(Collectors.toList());
            AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            model.addAttribute("user", user);
            model.addAttribute("courses", paging);
            model.addAttribute("pages", pages);
            model.addAttribute("subscriptions", indexService.getUserSubscriptions());

            return "course/subscriptions";
        } catch (ClassCastException e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }
}
