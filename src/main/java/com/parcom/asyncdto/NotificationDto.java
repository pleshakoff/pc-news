package com.parcom.asyncdto;

import com.parcom.news.services.notification.NotificationReceiverType;
import com.parcom.news.services.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationDto {

    @NotNull
    private  final NotificationType notificationType;

    @NotNull
    private  final NotificationReceiverType notificationReceiverType;

    @NotNull
    private final String  title;

    private final String  message;

    private final @NotNull String idObjectSender;

    @NotNull
    private final Long idUserSender;

    private Long idUserReceiver;

    private List<Long> customUsers;

}
