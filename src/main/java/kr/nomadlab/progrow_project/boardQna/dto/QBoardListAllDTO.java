package kr.nomadlab.progrow_project.boardQna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QBoardListAllDTO {
    private Long qnaBoardNo;
    private String title;
    private String writer;
    private Integer hit;
    private LocalDateTime regDate;
    private Long qReplyCount;
    private List<QBoardImageDTO> qBoardImages;
}
