package ru.gk.users.service;

public interface SubscriptionService {

    void subscribe(Long subscriberId, Long targetId);

    void unSubscribe(Long subscriberId, Long targetId);

}
