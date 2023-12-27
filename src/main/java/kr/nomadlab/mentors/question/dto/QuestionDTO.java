package kr.nomadlab.mentors.question.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Long qno; // 질문 고유번호
    private String memberId; // 회원 아이디
    private String writer; // 회원 닉네임
    private String title; // 질문 제목
    private String content; // 질문 내용
    private Long hit; // 질문 조회수
    private LocalDateTime addDate; // 질문 등록 날짜
    private Boolean isComplete;// 질문 해결 상태 유무
    private Long answerCount; // 질문 댓글 수
    private Long voteCount; // 질문 좋아요 수

    private List<QNAVoteDTO> voteList = new ArrayList<>(); // 게시글 추천 목록
    private List<QuestionTagDTO> tagList = new ArrayList<>(); // 게시글 해쉬태그 목록

    // 몇 시간 전에 작성되었는지 계산
    public String getElapsedTime() {
        LocalDateTime now = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(addDate, now);
        long days = ChronoUnit.DAYS.between(addDate, now);

        if (hours < 1) {
            return "방금 전";
        } else if (hours < 24) {
            return hours + "시간 전";
        } else if (days < 30) {
            return days + "일 전";
        } else {
            // 30일 이상이면 날짜 표시 (예: "2023-01-01 12:34:56")
            return addDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }
} 
