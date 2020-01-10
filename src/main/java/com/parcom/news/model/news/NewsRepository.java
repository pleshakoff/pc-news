package com.parcom.news.model.news;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends MongoRepository<News, String> {

    List<News> getNewsByIdGroup(@Param("idGroup") Long idGroup);


}
