package kr.nomadlab.mentors.member.domain;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenteeVO {
    private Long mno; // 멘티 고유 번호
    private String memberId; // 멘티 이메일
    private String region; // 멘티 지역
    private String nickname; // 멘티 닉네임
    private String devLanguage; // 멘티가 관심있는 개발언어
}
