package kr.nomadlab.progrow_project.boardReply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long rno; //리플 고유 번호
    private String content; //리플의 내용
    private String nickname; //리플 작성자
    private Long boardNo; //게시판의 글 고유번호

}
