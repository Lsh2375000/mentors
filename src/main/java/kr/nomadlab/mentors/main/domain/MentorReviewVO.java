package kr.nomadlab.mentors.main.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentorReviewVO {
    private Long mrNo;
    private Long mno;
    private String nickname;
    private Double score;
    private String review;
}
