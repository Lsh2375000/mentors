package kr.nomadlab.mentors.main.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentorReviewVO {
    private Long mrNo;
    private Long mentorMno;
    private Long menteeMno;
    private Double score;
    private String review;
}
