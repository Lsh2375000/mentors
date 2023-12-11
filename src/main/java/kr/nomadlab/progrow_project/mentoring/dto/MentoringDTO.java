package kr.nomadlab.progrow_project.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentoringDTO {
    private Long mNo; // 멘토링 세션방 고유 번호
    private String mentor_nickname; // 멘토 닉네임
    private String mentee_id; // 멘티 아이디
    private String region; // 오프라인일 경우 모일 지역
    private String devLanguage; // 멘토가 가르칠 프로그래밍 언어
    private String chatNo; // 채팅 채팅방 번호
    private String materials; // 멘토링 진행하면서 올리는 자료
    private boolean meeting; // 사전 만남 여부(1회만 가능)
    private boolean on_off; // 온 / 오프라인 여부
    private LocalDateTime addDate; // 기능 제공할 기간(기본 제공 두달)
    private int rentNo; // 공간 대여 했을 시 방 번호
    private boolean payment; // 결제 여부
    private String type; // 마이페이지 나누는 목적
}
