package kr.nomadlab.mentors.main.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentorReviewVO {
    private Long mrNo;
    private Long mbNo; // 게시물 번호
    private Long mentorMno; // 멘토 mno
    private Long menteeMno; // 멘티 mno
    private Double score; // 별점
    private String review; // 리뷰코멘트
    private LocalDateTime addDate; // 리뷰 작성일
}
