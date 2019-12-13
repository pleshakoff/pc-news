package com.parcom.news.services.notification;


import com.parcom.rest_template.RestTemplateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private static final String GROUP = "group";
    private static final String USER = "user";
    private static final String CUSTOM = "custom";

    private final RestTemplate restTemplate;

    @Value("${parcom.services.notification.host}")
    private String notificationHost;

    @Value("${parcom.services.notification.port}")
    private String notificationPort;


    @Async
    public void sendGroup(NotificationDto notificationDto) {
        send(notificationDto, GROUP);
    }

    @Async
    public void sendUser(NotificationDto notificationDto) {
        send(notificationDto, USER);
    }

    @Async
    public void sendCustom(NotificationDto notificationDto) {
        send(notificationDto, CUSTOM);
    }




    private void send(NotificationDto notificationDto, String path)
    {
        log.info("Send notification");

        URI uri = UriComponentsBuilder.newInstance().scheme(RestTemplateUtils.scheme).host(notificationHost).port(notificationPort).path("/send/").path(path).build().toUri();

        HttpEntity<NotificationDto> requestBody = new HttpEntity<>(notificationDto, RestTemplateUtils.getHttpHeaders());
        try {
            restTemplate.postForEntity(uri, requestBody,String.class);
        }
        catch (Exception e)
        {
            log.error("Sending error",e);
        }
    }






}
