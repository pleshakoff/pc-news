package com.parcom.news.services.notification;

import org.springframework.scheduling.annotation.Async;

public interface NotificationService {
    @Async
    void sendGroup(NotificationDto notificationDto);

    @Async
    void sendUser(NotificationDto notificationDto);

    @Async
    void sendCustom(NotificationDto notificationDto);
}
