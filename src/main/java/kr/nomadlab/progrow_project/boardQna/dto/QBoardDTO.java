package kr.nomadlab.progrow_project.boardQna.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QBoardDTO {
    private Long qnaBoardNo; // 게시글 No
    private String id; // ID
    private Integer hit; // 조회수
    private String nickname; // 닉네임
    private String content; // 게시글 내용
    private String title; // 게시글 제목
    private LocalDateTime regDate; // 등록일
    private LocalDateTime modDate; // 수정일
    private List<QBoardImageDTO> qBoardImages;


}
