package kr.nomadlab.mentors.question.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QNAVoteVO {

    private Long vno; // 추천 고유번호
    private Long qno; // 질문 고유번호
    private Long mno; // 회원 고유번호
}
