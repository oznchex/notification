package alarmAndEmail.notification;

import alarmAndEmail.BaseEntity;
import alarmAndEmail.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    // 회원과 1대 다 관계이다.
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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
    @Enumerated(value = STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType;

    public Notification(
            final Long id,
            final Member member,
            final String content,
            final NotificationType notificationType
    ) {
        this.id = id;
        this.member = member;
        this.isDeleted = false;
        this.isRead = false;
        this.content = content;
        this.notificationType = notificationType;
    }

    public Notification(
            final Member member,
            final String content,
            final NotificationType notificationType
    ) {
        this(null, member, content, notificationType);
    }
}
