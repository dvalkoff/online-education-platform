package ru.mirea.valkov.education.platform.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "course_id",
            nullable = false
    )
    private Course course;

    @Column(nullable = false)
    private String theme;

    @Column(
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            columnDefinition = "TEXT"
    )
    private String homework;

    @Column(nullable = false)
    private LocalDateTime startsAt;

    @Column(nullable = false)
    private LocalDateTime endsAt;

    public Lesson(Course course, String theme, LocalDateTime startsAt, LocalDateTime endsAt) {
        this.course = course;
        this.theme = theme;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public Lesson(Course course, String theme, String description, String homework, LocalDateTime startsAt, LocalDateTime endsAt) {
        this.course = course;
        this.theme = theme;
        this.description = description;
        this.homework = homework;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }
}
