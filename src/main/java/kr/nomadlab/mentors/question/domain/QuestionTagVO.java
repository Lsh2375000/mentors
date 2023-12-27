package kr.nomadlab.mentors.question.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionTagVO {
    private Long qtNo; // 태그 고유번호
    private Long qno; // 질문 고유번호
    private String tagName; // 태그 이름
}
