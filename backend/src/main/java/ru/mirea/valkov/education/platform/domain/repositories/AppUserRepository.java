package ru.mirea.valkov.education.platform.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.valkov.education.platform.domain.entities.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    @Modifying
    @Query("UPDATE AppUser SET isEnabled = ?2 WHERE id = ?1")
    void updateEnabledById(Long id, boolean state);
}
