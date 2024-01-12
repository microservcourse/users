package ru.gk.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gk.users.entity.Skills;

import java.util.Optional;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
    Optional<Skills> getBySkillName(String skillName);
}