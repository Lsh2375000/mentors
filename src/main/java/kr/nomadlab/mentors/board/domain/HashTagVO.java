package kr.nomadlab.mentors.board.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HashTagVO {
    private Long htNo; // 해쉬태그 고유번호
    private Long boardNo; // 게시글 고유번호
    private String tagName; // 태그 이름
}
