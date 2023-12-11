package kr.nomadlab.progrow_project.boardQna.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.nomadlab.progrow_project.boardQna.dto.QBoardImageDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QBoardListAllVO {
    private Long qnaBoardNo; // 게시글 No
    private String id; // ID
    private Integer hit; // 조회수
    private String nickname; // 닉네임
    private String content; // 게시글 내용
    private String title; // 게시글 제목

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate; // 리플 작성일자

    @JsonIgnore
    private LocalDateTime modDate; // 리플 수정일자

    private int qReplyCount; // 댓글 수
    private List<QBoardImageDTO> qBoardImages; // 이미지
}
