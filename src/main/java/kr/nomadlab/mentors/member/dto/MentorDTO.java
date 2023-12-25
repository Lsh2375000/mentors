package kr.nomadlab.mentors.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorDTO {
    private Long mno; // 멘토 고유 번호
    private String memberId; // 멘토 이메일
    private String nickname; // 닉네임
    private String devLanguage; // 멘토전공 언어
    private String fileNames; // 멘토 포트폴리오
    private String intro; // 멘토 소개
    private int ranking; // 멘토링 횟수에 따른 등급
    private boolean isMentoring; // 현재 멘토링 참여 여부
    private Double score;
    private String univName;
    private String major;
}
