package com.parcom.news.model.news;

import com.parcom.exceptions.ParcomException;
import com.parcom.news.SpringSecurityTestConfiguration;
import com.parcom.news.services.notification.NotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.parcom.news.SpringSecurityTestConfiguration.ID_USER_ADMIN;
import static com.parcom.news.SpringSecurityTestConfiguration.ID_USER_ADMIN_GROUP;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {NewsServiceImplTestConfiguration.class, SpringSecurityTestConfiguration.class})
class NewsServiceImplTest {

    private static final String ID_NEWS_ONE = "1";
    private static final String ID_NEWS_TWO = "2";
    private static final String MESSAGE = "message";
    private static final String TITLE = "title";
    public static final long ID = 1L;


    @Autowired
    NewsService newsService;

    @MockBean
    NewsRepository newsRepository;

    @MockBean
    NotificationService notificationService;


    @Test
    @WithUserDetails("admin@parcom.com")
    void getNews() {
        Mockito.when(newsRepository.getNewsByIdGroup(ID_USER_ADMIN_GROUP)).
                thenReturn(Arrays.asList(News.builder().id(ID_NEWS_ONE).build(), News.builder().id(ID_NEWS_TWO).build())
                );


        List<NewsResource> news = newsService.getNews();

        assertEquals(2,news.size());
    }

    @Test
    @WithUserDetails("admin@parcom.com")
    void create() {
        Mockito.when(newsRepository.save(Mockito.any(News.class))).thenAnswer(i -> i.getArguments()[0]);
        NewsDto newsDto = NewsDto.builder().message(MESSAGE).title(TITLE).build();
        NewsResource news = newsService.create(newsDto);

        assertAll(
             () -> assertEquals(MESSAGE,news.getMessage()),
             () ->assertEquals(TITLE,news.getTitle()),
             () -> assertEquals(Long.toString( ID_USER_ADMIN),news.getAuthor())
        );
    }

    @Test
    @WithUserDetails("parent@parcom.com")
    void createByParent() {
        Assertions.assertThrows(AccessDeniedException.class,() -> newsService.create(null));

    }


    @Test
    @WithUserDetails("admin@parcom.com")
    void update() {


        News news = News.builder().title("badtitle").message("badTitle").idUser(ID_USER_ADMIN).build();
        Mockito.when(newsRepository.findById(ID_NEWS_ONE)).thenReturn(Optional.of(news));
        Mockito.when(newsRepository.save(Mockito.any(News.class))).thenAnswer(i -> i.getArguments()[0]);

        NewsDto newsDto = NewsDto.builder().message(MESSAGE).title(TITLE).build();
        NewsResource newsUpdated = newsService.update(ID_NEWS_ONE,newsDto);

        assertAll(
                () -> assertEquals(MESSAGE,newsUpdated.getMessage()),
                () ->assertEquals(TITLE,newsUpdated.getTitle()),
                () -> assertEquals(Long.toString( ID_USER_ADMIN),newsUpdated.getAuthor())
        );
    }

    @Test
    @WithUserDetails("member@parcom.com")
    void updateNotYours() {

        News news = News.builder().idUser(ID_USER_ADMIN).build();
        Mockito.when(newsRepository.findById(ID_NEWS_ONE)).thenReturn(Optional.of(news));
        Mockito.when(newsRepository.save(Mockito.any(News.class))).thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertThrows(ParcomException.class,() -> newsService.update(ID_NEWS_ONE,null));
    }


    @Test
    @WithUserDetails("parent@parcom.com")
    void updateByParent() {
        Assertions.assertThrows(AccessDeniedException.class,() -> newsService.update(ID_NEWS_ONE,null));
    }


    @Test
    @WithUserDetails("admin@parcom.com")
    void delete() {
        News news = News.builder().idUser(ID_USER_ADMIN).build();
        Mockito.when(newsRepository.findById(ID_NEWS_ONE)).thenReturn(Optional.of(news));
        newsService.delete(ID_NEWS_ONE);
    }

    @Test
    @WithUserDetails("member@parcom.com")
    void deleteNotMy() {
        News news = News.builder().idUser(ID_USER_ADMIN).build();
        Mockito.when(newsRepository.findById(ID_NEWS_ONE)).thenReturn(Optional.of(news));
        Assertions.assertThrows(ParcomException.class,() -> newsService.delete(ID_NEWS_ONE));
    }

    @Test
    @WithUserDetails("parent@parcom.com")
    void deleteByParent() {
        Assertions.assertThrows(AccessDeniedException.class,() -> newsService.delete(ID_NEWS_ONE));
    }


}