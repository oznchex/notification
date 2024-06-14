package alarmAndEmail.notification;

import alarmAndEmail.BaseEntity;
import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    // 삭제 여부
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // 읽음 여부 (열람)
    @Column(name = "is_read")
    private Boolean isRead;

    // 쪽지 내용
    @Column(name = "content")
    private String content;

    // 쪽지의 타입
    @Column(name = "type")
    private String type;
}
