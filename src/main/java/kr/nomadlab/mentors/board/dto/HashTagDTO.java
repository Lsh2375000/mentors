package kr.nomadlab.mentors.board.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HashTagDTO {
    private Long htNo; // 해쉬태그 고유번호
    private Long boardNo; // 게시글 고유번호
    private String tagName; // 태그 이름
    private Long tagCount; // 태그 수
}
