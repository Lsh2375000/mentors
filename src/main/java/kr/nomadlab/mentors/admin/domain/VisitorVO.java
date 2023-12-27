package kr.nomadlab.mentors.admin.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VisitorVO {

    private Long vNo; // 테이블 고유번호
    private LocalDate visitDate; // 방문날짜
    private boolean isLogin; // 로그인 여부
    private String sessionId; // 세션Id
}
