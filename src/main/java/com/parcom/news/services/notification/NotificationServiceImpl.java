package com.parcom.news.services.notification;


import com.parcom.network.Network;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private static final String GROUP = "group";
    private static final String USER = "user";
    private static final String CUSTOM = "custom";

    private final Network network;



    @Override
    public void sendGroup(NotificationDto notificationDto) {
        send(notificationDto, GROUP);
    }

    @Override
    public void sendUser(NotificationDto notificationDto) {
        send(notificationDto, USER);
    }

    @Override
    public void sendCustom(NotificationDto notificationDto) {
        send(notificationDto, CUSTOM);
    }



    private void send(NotificationDto notificationDto, String path)
    {
        log.info("Send notification");
        network.callPost("notifier",String.class,notificationDto,"send",path);

    }






}
