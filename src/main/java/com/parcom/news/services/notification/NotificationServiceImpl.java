package com.parcom.news.services.notification;


import com.parcom.network.Network;
import com.parcom.news.model.news.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static com.parcom.news.services.notification.NotificationReceiverType.GROUP;
import static com.parcom.news.services.notification.NotificationType.NEWS;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final KafkaTemplate<String,NotificationDto> notificationDtoKafkaTemplate;

    @Value("${parcom.kafka.topic.notification}")
    private String notificationTopic;



    private void sendToKafka(NotificationDto notificationDto) {

        ListenableFuture<SendResult<String, NotificationDto>> future =
                notificationDtoKafkaTemplate.send(notificationTopic, notificationDto);

        future.addCallback(new ListenableFutureCallback<SendResult<String, NotificationDto>>() {

            @Override
            public void onSuccess(SendResult<String, NotificationDto> result) {
                log.info("Send notification news id {}",notificationDto.getIdObjectSender());
            }

            @Override
            public void onFailure(Throwable ex) {
               log.error(String.format("Send notification failure id %s",notificationDto.getIdObjectSender()),ex);
            }
        });
    }




    @Override
    public void send(News news) {
        NotificationDto notificationDto = new NotificationDto(NEWS, GROUP, news.getTitle(), news.getMessage(), news.getId(), news.getIdUser());
        sendToKafka(notificationDto);
    }
}
