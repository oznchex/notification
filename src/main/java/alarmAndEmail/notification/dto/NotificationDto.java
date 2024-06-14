package alarmAndEmail.notification.dto;

import alarmAndEmail.notification.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationDto {
    private final NotificationType notificationType;
    private final String content;
}
