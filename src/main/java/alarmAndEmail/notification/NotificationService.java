package alarmAndEmail.notification;

import alarmAndEmail.mail.MailSender;
import alarmAndEmail.member.Member;
import alarmAndEmail.member.MemberRepository;
import alarmAndEmail.notification.dto.NotificationDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static alarmAndEmail.notification.NotificationType.RECEIVE_PROFILE_MATCHING;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final MemberRepository memberRepository;
    private final NotificationRepository notificationRepository;
    private final MailSender mailSender;

    // 회원 조회
    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }

    // 매칭 요청이 나(내 이력서)한테 발생했을때 (나는 내 이력서에 대한 매칭 요청을 수신한다.)
    // 매칭 관리 테이블에 있어야 함
    // 누가 누구한테 매칭 요청을 보냈는지만 중요
    public void addReceiveProfileMatching(final Long memberId, final Long toProfileId) {
        // 기존 기능

        // 원래 기능을 마무리하고, ex) 매칭 요청
        // Notification 기능을 호출한다.
        final NotificationType notificationType = RECEIVE_PROFILE_MATCHING;
        final Member member = getMember(memberId);

        // 이름 저장
        // 멤버의 이름과 추가 정보 사용
        final String content = notificationType.getMessage(member.getNickName());

        // 이름 추출 후 내용 안에 저장
        NotificationDto notificationDto = NotificationDto.builder()
                .notificationType(notificationType)
                .content(content)
                .build();

        // 알림을 저장한다.
        addNotification(notificationDto, toProfileId);
    }


    // NotificationService 에 있어야 함
    // 누구에게 알림이 간다는 뜻의 변수 조합
    private void addNotification(
            final NotificationDto notificationDto,
            final Long toId
    ) {
        // 누구에게 알림을 보낼지를 찾아서 정해야 함
        final Member member = getMember(toId);

        final Notification notification = new Notification(
                member,
                notificationDto.getContent(),
                notificationDto.getNotificationType()
        );

        notificationRepository.save(notification);

        try {
            mailSender.sendNotificationEmail(notification);
        } catch (MessagingException e) {
            throw new RuntimeException("MAIL_SEND_FAIL");
        }
    }

}
