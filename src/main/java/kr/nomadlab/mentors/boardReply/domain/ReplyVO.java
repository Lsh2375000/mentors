package kr.nomadlab.mentors.boardReply.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyVO{
    private Long rno; // 댓글 고유 번호
    private Long parentNo; // 부모 댓글 번호
    private String content; // 댓글 내용
    private String writer; // 댓글 작성자
    private LocalDateTime addDate; // 댓글 작성 날짜
    private Long boardNo; // 게시글 고유번호

    private List<ReplyVO> children = new ArrayList<>();
}
