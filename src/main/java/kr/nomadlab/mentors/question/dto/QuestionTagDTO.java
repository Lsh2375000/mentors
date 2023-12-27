package kr.nomadlab.mentors.question.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTagDTO {
    private Long qtNo; // 태그 고유번호
    private Long qno; // 질문 고유번호
    private String tagName; // 태그 이름
    private Long tagCount; // 태그 수
}
