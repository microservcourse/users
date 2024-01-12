package ru.gk.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gk.users.entity.Subscription;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findBySubscriberUserIdAndTargetUserUserId(Long from, Long to);
}