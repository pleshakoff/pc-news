package com.parcom.news.model.news;

import com.parcom.exceptions.NotFoundParcomException;
import com.parcom.exceptions.ParcomException;
import com.parcom.news.services.notification.NotificationDto;
import com.parcom.news.services.notification.NotificationService;
import com.parcom.security_client.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.parcom.news.services.notification.NotificationType.NEWS;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NotificationService notificationService;

    @Override
    public List<NewsResource> getNews() {
        return newsRepository.getNewsByIdGroup(UserUtils.getIdGroup()).stream().map(NewsResource::new).collect(Collectors.toList());
    }

    private News getById(String idNews) {
        return newsRepository.findById(idNews).orElseThrow(() -> new NotFoundParcomException("news.not.found"));
    }


    @Override
    public NewsResource create(NewsDto newsDto) {
        final News news = newsRepository.save(
                News.builder().
                        dateTime(LocalDateTime.now()).
                        title(newsDto.getTitle()).
                        message(newsDto.getMessage()).
                        idGroup(UserUtils.getIdGroup()).
                        idUser(UserUtils.getIdUser()).
                        build()
        );
        notificationService.send(news);
        return new NewsResource(news);
    }

    @Override
    public NewsResource update(String id, NewsDto newsDto) {
        News news = getById(id);
        if (!news.getIdUser().equals(UserUtils.getIdUser())) {
            throw new ParcomException("news.you_not_news_owner");
        }
        news.setTitle(newsDto.getTitle());
        news.setMessage(newsDto.getMessage());
        return new NewsResource(newsRepository.save(
                news
        ));
    }

    @Override
    public void delete(String id) {
        News news = getById(id);
        if (!news.getIdUser().equals(UserUtils.getIdUser())) {
            throw new ParcomException("news.you_not_news_owner");
        }
        newsRepository.deleteById(id);
    }



}
