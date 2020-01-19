package com.parcom.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
@EnableAsync
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class NewsApplication {


    //	docker run --name classroom_pg -e POSTGRES_PASSWORD=parcom -d -p 5432:5432 postgres:11
//  docker image build -t pleshakoff/pc-news:hw1 .
//  docker image push pleshakoff/pc-news:hw1
    public static void main(String[] args) {
        SpringApplication.run(NewsApplication.class, args);
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }





}

