package kr.nomadlab.progrow_project.board.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardVO {

    private Integer boardNo;
    private String id;
    private String nickname;
    private String title;
    private String content;
    private Integer hit;
    private LocalDateTime addDate;
    private String fileNames;

}
