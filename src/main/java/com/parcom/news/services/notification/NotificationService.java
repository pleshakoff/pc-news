package com.parcom.news.services.notification;

import com.parcom.news.model.news.News;
import org.springframework.scheduling.annotation.Async;

public interface NotificationService {
    @Async
    void send(News news);
}
