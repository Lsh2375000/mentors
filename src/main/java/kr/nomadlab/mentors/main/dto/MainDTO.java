package kr.nomadlab.mentors.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MainDTO {
    private Long mbNo; // 메인게시물 번호
    private Long mno; // 회원번호(멘토)
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
    private int price; // 가격
    private int curPeople; // 현재 인원수
    private int maxPeople; // 최대인원수
    private double score; // 수강평점수
    private String roomId; // 채팅방 ID

    private int isMentoring; // 멘토가 작성한 수업이 있는지 없는지
    private boolean isApplied; // 멘토링 신청여부 확인
}
