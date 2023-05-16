package ru.mirea.valkov.education.platform.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.dto.responses.CourseInfo;
import ru.mirea.valkov.education.platform.dto.responses.UserInfo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoMapper {
    private final CourseInfoMapper courseInfoMapper;

    public UserInfo mapFromEntity(AppUser appUser, Collection<Course> ownCourses) {
        return UserInfo.builder()
                .id(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .username(appUser.getUsername())
                .subscriptions(mapSubscriptions(appUser.getSubscriptions()))
                .ownCourses(mapSubscriptions(ownCourses))
                .build();
    }

    private List<CourseInfo> mapSubscriptions(Collection<Course> courses) {
        return courses.stream()
                .map(courseInfoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
