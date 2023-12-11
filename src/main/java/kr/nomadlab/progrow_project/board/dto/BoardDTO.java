package kr.nomadlab.progrow_project.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Integer boardNo;
    private String id;
    private String nickname;
    private String title;
    private String content;
    private Integer hit;
    private LocalDateTime addDate;
    private String fileNames;
}
