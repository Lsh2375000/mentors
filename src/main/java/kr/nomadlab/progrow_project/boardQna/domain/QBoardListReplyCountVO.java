package kr.nomadlab.progrow_project.boardQna.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QBoardListReplyCountVO {
    private Long qnaBoardNo;
    private String title;
    private String content;
    private Integer hit; // 조회수
    private String nickname;
    private LocalDateTime regDate;
    private Long qReplyCount;
}
