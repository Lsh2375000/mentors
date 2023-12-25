package kr.nomadlab.mentors.projectReply.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectReplyVO {
    private Long pjrNo; // 댓글 고유 번호
    private Long parentNo; // 부모 댓글 번호
    private Long mno; // 회원 고유 번호
    private Long projectNo; // 게시글 고유 번호
    private String content; // 댓글 내용
    private String writer; // 댓글 작성자
    private LocalDateTime addDate; // 댓글 작성 날짜
    
    @Builder.Default
    private boolean cmEdtNot = true; // 댓글 수정 유무

    private List<ProjectReplyVO> children = new ArrayList<>();
}
