package com.parcom.news.model.news;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewsInit {

    private  final  NewsRepository newsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        newsRepository.save(News.builder().idGroup(21L).
                idUser(1L).
                dateTime(LocalDateTime.now()).
                title("Нужно больше денег").
                message("Уважаемые родители, необходимо СРОЧНО скинуться на покупку слона в класс. Слон нужен для урока биологии").
                build());

        newsRepository.save(News.builder().idGroup(21L).
                idUser(1L).
                dateTime(LocalDateTime.now()).
                title("Уроки отменяются").
                message("В связи с проведением ежегодных праздничных торжества, все уроки отменяются. Ура!!!").
                build());

    }

}
