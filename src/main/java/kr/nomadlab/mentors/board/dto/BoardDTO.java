package kr.nomadlab.mentors.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long boardNo; // 게시글 고유번호
    private String memberId; // 회원 아이디
    private String writer; // 회원 닉네임
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private Long hit; // 게시글 조회수
    private LocalDateTime addDate; // 게시글 등록 날짜
    private Long replyCount; // 게시글 댓글 수
    private Long likeCount; // 좋아요 수

    private List<BoardLikeDTO> likeList = new ArrayList<>(); // 게시글 좋아요 목록
    private List<HashTagDTO> tagList = new ArrayList<>(); // 게시글 해쉬태그 목록

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
