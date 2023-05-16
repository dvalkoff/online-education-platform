package ru.mirea.valkov.education.platform.dto.mappers;

import org.springframework.stereotype.Service;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.dto.responses.CourseInfo;

@Service
public class CourseInfoMapper {
    public CourseInfo mapToDto(Course course) {
        return CourseInfo.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .ownerUsername(course.getOwner().getUsername())
                .build();
    }

    public Course mapToEntity(CourseInfo courseInfo, AppUser appUser) {
        return new Course(
                courseInfo.getTitle(),
                courseInfo.getDescription(),
                appUser
        );
    }
}
