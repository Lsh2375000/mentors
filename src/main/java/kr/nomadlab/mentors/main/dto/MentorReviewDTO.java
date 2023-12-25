package kr.nomadlab.mentors.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorReviewDTO {
    private Long mrNo;
    private Long mbNo; // 게시물 번호
    private Long mentorMno; // 멘토 mno
    private Long menteeMno; // 멘티 mno
    private Double score; // 별점
    private String review; // 리뷰코멘트
    private LocalDateTime addDate; // 리뷰 작성일

    private boolean isReview; // 리뷰 작성 했는지 여부
}
