package ru.mirea.valkov.education.platform.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllBySubscribers(AppUser appUser);

    Optional<Course> findByTitle(String title);

    Page<Course> findAllBySubscribers(AppUser appUser, Pageable pageable);

    List<Course> findAllByOwner(AppUser appUser);
}
