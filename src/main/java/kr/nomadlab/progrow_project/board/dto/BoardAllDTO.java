package kr.nomadlab.progrow_project.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardAllDTO {
    private Long boardNo;
    private String title;
    private String content;
    private String nickname;
    private LocalDate addDate;
    private Integer hit;
    private String fileNames;

    private Long replyCount;

}
