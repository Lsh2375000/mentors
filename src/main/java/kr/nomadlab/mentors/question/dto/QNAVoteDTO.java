package kr.nomadlab.mentors.question.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QNAVoteDTO {

    private Long vno; // 추천 고유번호
    private Long qno; // 질문 고유번호
    private Long mno; // 회원 고유번호
}
