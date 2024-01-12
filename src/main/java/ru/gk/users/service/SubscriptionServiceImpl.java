package ru.gk.users.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.gk.users.entity.Subscription;
import ru.gk.users.entity.User;
import ru.gk.users.repository.SubscriptionRepository;

@Service
@AllArgsConstructor
@Log4j2
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void subscribe(Long subscriberId, Long targetId) {

        User subscriber = userService.getUserById(subscriberId);
        User target = userService.getUserById(targetId);

        Subscription subscription = new Subscription();
        subscription.setSubscriber(subscriber);
        subscription.setTargetUser(target);

        subscriptionRepository.save(subscription);
        log.info("UserId {} subscribed to {}", subscriber.getUserId(), target.getUserId());

    }

    @Override
    @Transactional
    public void unSubscribe(Long subscriberId, Long targetId) {
        subscriptionRepository.findBySubscriberUserIdAndTargetUserUserId(subscriberId, targetId)
                .ifPresentOrElse(i -> {
                            subscriptionRepository.delete(i);
                            log.info("UserId {} unsubscribed from {}", subscriberId, targetId);
                        },
                        () -> {
                            throw new RuntimeException("not found");
                        });

    }


}
