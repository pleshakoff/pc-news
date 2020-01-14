package com.parcom.news;

import com.parcom.news.services.notification.NotificationDto;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

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

