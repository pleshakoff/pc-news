package com.parcom.news.model.news;


import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class NewsResource {

    private final String id;
    private final String title;
    private final String message;
    private final   String author;
    private final LocalDateTime dateTime;

    public NewsResource(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.message = news.getMessage();
        this.author =  Optional.ofNullable(news.getIdUser()).map(Object::toString).orElse(null);
        this.dateTime = news.getDateTime();
    }
}
