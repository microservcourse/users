package ru.gk.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gk.users.entity.UserSkills;

import java.util.Optional;

public interface UserSkillsRepository extends JpaRepository<UserSkills, Long> {
    Optional<UserSkills> findByUserUserIdAndSkillSkillId(Long from, Long to);
}