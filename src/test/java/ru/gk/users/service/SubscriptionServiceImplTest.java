package ru.gk.users.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gk.users.dto.UserRequest;
import ru.gk.users.entity.Subscription;
import ru.gk.users.entity.User;
import ru.gk.users.repository.SubscriptionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    private UserRequest userRequest;
    private User user;


    @Test
    void testSubscribe() {
        Long subscriberId = 1L;
        Long targetId = 2L;
        User subscriber = new User();
        User target = new User();

        when(userService.getUserById(subscriberId)).thenReturn(subscriber);
        when(userService.getUserById(targetId)).thenReturn(target);
        subscriptionService.subscribe(subscriberId, targetId);

        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
    }

    @Test
    void testUnSubscribe() {
        Long subscriberId = 1L;
        Long targetId = 2L;
        Subscription subscription = new Subscription();

        when(subscriptionRepository.findBySubscriberUserIdAndTargetUserUserId(subscriberId, targetId))
                .thenReturn(Optional.of(subscription));

        subscriptionService.unSubscribe(subscriberId, targetId);
        verify(subscriptionRepository, times(1)).delete(subscription);
    }

    @Test
    void testUnSubscribeNotFound() {
        Long subscriberId = 1L;
        Long targetId = 2L;
        when(subscriptionRepository.findBySubscriberUserIdAndTargetUserUserId(subscriberId, targetId))
                .thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> subscriptionService.unSubscribe(subscriberId, targetId));
    }
}


