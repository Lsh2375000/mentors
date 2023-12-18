package kr.nomadlab.mentors.board.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardLikeVO {
    private Long boardNo; // 게시글 고유번호
    private Long mno; // 회원 고유번호
}
