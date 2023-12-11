package kr.nomadlab.progrow_project.member.domain;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenteeVO {
    private Long menteeNo; // 멘티 고유 번호
    private String mentee_id; // 멘티 이메일
    private String passwd; // 멘티 비밀번호
    private String name; // 멘티 이름
    private String region; // 멘티 지역
    private String nickname; // 멘티 닉네임
    private String devLanguage; // 멘티가 관심있는 개발언어
    private int mNo; // 현재 참여하고 있는 멘토링방 번호
    private int mNum; // 멘토링 참여 횟수
    private String type; // 멘티 타입
    private boolean isMentoring; // 현재 멘토링 참여 여부
}
