package ru.mirea.valkov.education.platform.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllUsersCourses(AppUser appUser) {
        return courseRepository.findAllBySubscribers(appUser);
    }

    public Page<Course> getAllUsersCoursesPageable(AppUser appUser, Pageable pageable) {
        return courseRepository.findAllBySubscribers(appUser, pageable);
    }

    public Page<Course> getCoursesPageable(PageRequest pageable) {
        return courseRepository.findAll(pageable);
    }

    public Course getCourseByTitle(String title) {
        return courseRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalStateException(String.format("Course with title %s not found", title)));
    }

    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> findCourseByTitle(String courseTitle) {
        return courseRepository.findByTitle(courseTitle);
    }

    public List<Course> findCoursesByOwner(AppUser appUser) {
        return courseRepository.findAllByOwner(appUser);
    }

    @Transactional
    public void subscribeUserToCourse(String courseTitle, AppUser appUser) {
        Course course = this.getCourseByTitle(courseTitle);
        course.addSubscriber(appUser);
    }

    @Transactional
    public void unsubscribeUserFromCourse(String courseTitle, AppUser appUser) {
        Course course = this.getCourseByTitle(courseTitle);
        course.removeSubscriber(appUser);
    }
}
