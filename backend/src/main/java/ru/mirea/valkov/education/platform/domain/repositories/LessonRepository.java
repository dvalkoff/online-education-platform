package ru.mirea.valkov.education.platform.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;
import ru.mirea.valkov.education.platform.domain.entities.Course;
import ru.mirea.valkov.education.platform.domain.entities.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByCourse_SubscribersOrderByStartsAt(AppUser appUser);

    List<Lesson> findAllByCourse(Course course);
}
