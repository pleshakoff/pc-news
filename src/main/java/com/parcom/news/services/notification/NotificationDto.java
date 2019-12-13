package com.parcom.news.services.notification;

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
    private final String  title;

    private final String  message;

    private final @NotNull String idObjectSender;

    @NotNull
    private final Long idUserSender;

    private Long idUserReceiver;

    private List<Long> customUsers;

}
