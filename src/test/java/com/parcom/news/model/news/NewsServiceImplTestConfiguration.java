package com.parcom.news.model.news;

import com.parcom.news.services.notification.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class NewsServiceImplTestConfiguration {

    @Bean
    NewsService newsService(NewsRepository newsRepository, NotificationService notificationService) {
        return  new NewsServiceImpl(newsRepository,notificationService);
    }
}
