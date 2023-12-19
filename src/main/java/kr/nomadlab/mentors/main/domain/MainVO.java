package kr.nomadlab.mentors.main.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MainVO {
    private Long mbNo; // 메인게시물 번호
    private Long mNo; // 회원번호(멘토)
    private String paidFree; // 무료인지 유료인지
    private String nickName; // 멘토닉네임
    private String devLanguage; // 멘토 전공언어
    private String title; // 게시물 제목
    private String position; // 직무
    private String career; // 경력
    private String content; // 정보
    private LocalDate startDate; // 수업 시작일
    private LocalDate endDate; // 수업 종료일
    private LocalDateTime addDate; // 등록일
    private double score; // 수강평점수
    private String price; // 가격
}
