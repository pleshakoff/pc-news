package com.parcom.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NewsApplication {


    //	docker run --name classroom_pg -e POSTGRES_PASSWORD=parcom -d -p 5432:5432 postgres:11
//  docker image build -t pleshakoff/pc-news:hw1 .
//  docker image push pleshakoff/pc-news:hw1
    public static void main(String[] args) {
        SpringApplication.run(NewsApplication.class, args);
    }





}

