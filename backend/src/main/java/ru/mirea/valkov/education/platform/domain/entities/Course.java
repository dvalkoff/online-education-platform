package ru.mirea.valkov.education.platform.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;


    @Column(
            unique = true,
            nullable = false
    )
    private String title;

    @Column(
            columnDefinition = "TEXT"
    )
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "owner_id",
            nullable = false
    )
    private AppUser owner;

    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<AppUser> subscribers;

    public Course(String title, AppUser owner) {
        this.owner = owner;
        this.title = title;
        this.subscribers = Collections.EMPTY_SET;
    }

    public Course(String title, String description, AppUser owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.subscribers = Collections.EMPTY_SET;
    }

    public void addSubscriber(AppUser appUser) {
        subscribers.add(appUser);
    }

    public void removeSubscriber(AppUser appUser) {
        subscribers.remove(appUser);
    }

    @Transient
    public Integer getCountSubscribers() {
        return subscribers.size();
    }
}
