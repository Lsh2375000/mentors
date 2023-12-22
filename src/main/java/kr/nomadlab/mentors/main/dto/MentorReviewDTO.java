package kr.nomadlab.mentors.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorReviewDTO {
    private Long mrNo;
    private Long mentorMno;
    private Long menteeMno;
    private Double score;
    private String review;
}
