package ru.gk.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gk.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
