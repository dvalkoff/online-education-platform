package ru.mirea.valkov.education.platform.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.services.AppUserService;
import ru.mirea.valkov.education.platform.domain.services.CourseService;
import ru.mirea.valkov.education.platform.dto.mappers.UserInfoMapper;
import ru.mirea.valkov.education.platform.dto.responses.UserInfo;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final AppUserService appUserService;
    private final UserInfoMapper userInfoMapper;
    private final CourseService courseService;

    @GetMapping("/{username}")
    public UserInfo getUserById(@PathVariable String username) {
        AppUser appUser = appUserService.loadUserByUsername(username);
        List<Course> courses = courseService.findCoursesByOwner(appUser);
        return userInfoMapper.mapFromEntity(appUser, courses);
    }

}
