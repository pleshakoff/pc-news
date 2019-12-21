package com.parcom.news.model.news;

import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface NewsService {
    List<NewsResource> getNews();

    @Secured({"ROLE_ADMIN", "ROLE_MEMBER"})
    NewsResource create(NewsDto newsDto);

    @Secured({"ROLE_ADMIN", "ROLE_MEMBER"})
    NewsResource update(Long id, NewsDto newsDto);

    @Secured({"ROLE_ADMIN", "ROLE_MEMBER"})
    void delete(Long id);
}
