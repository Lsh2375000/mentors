package kr.nomadlab.mentors.boardReply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long rno; // 댓글 고유 번호
    private Long parentNo; // 부모 댓글 번호
    private Long mno; // 회원 고유 번호
    private Long boardNo; // 게시글 고유번호
    private String content; // 댓글 내용
    private String writer; // 댓글 작성자
    private LocalDateTime addDate; // 댓글 작성 날짜

    @Builder.Default
    private boolean cmEdtNot = true; // 댓글 수정 유무

    List<ReplyDTO> children = new ArrayList<>(); // 대댓글 목록
}
