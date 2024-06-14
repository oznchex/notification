package alarmAndEmail.notification;

import alarmAndEmail.BaseEntity;
import alarmAndEmail.member.Member;
import jakarta.persistence.*;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
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
    @Column(name = "type")
    private String type;
}
