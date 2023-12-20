package kr.nomadlab.mentors.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenteeDTO {
    private Long mno; // 멘티 고유 번호
    private String memberId; // 멘티 이메일
    private String nickname; // 멘티 닉네임
    private String devLanguage; // 멘티가 관심있는 개발언어
}
