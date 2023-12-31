package kr.nomadlab.mentors.admin.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorDTO {
    private Long vNo; // 테이블 고유번호
    private LocalDate visitDate; // 방문날짜
    private boolean isLogin; // 로그인 여부
    private String sessionId; // 세션Id

    private boolean isSameId; // 동일한 세션 Id 있는지 여부
}
