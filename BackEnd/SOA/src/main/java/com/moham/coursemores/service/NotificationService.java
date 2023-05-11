package com.moham.coursemores.service;

import com.moham.coursemores.dto.notification.NotificationResDto;
import java.util.List;

public interface NotificationService {

    List<NotificationResDto> getMyNotificationList(Long userId);

    void makeNotification(Long userId, Long courseId, int messageType);

    void deleteNotification(Long userId, Long notificationId);

}