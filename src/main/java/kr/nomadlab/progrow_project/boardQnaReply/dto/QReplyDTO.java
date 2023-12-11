package kr.nomadlab.progrow_project.boardQnaReply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QReplyDTO {

    private Long qnaRno; // Q&A 게시판의 리플 고유번호
    private Long qnaBoardNo; // Q&A 게시판의 글 고유번호
    private String content; // Q&A 게시판의 리플 내용
    private String nickname; // Q&A 게시판의 리플 작성자 닉네임
    private String id; // Q&A 게시판의 리플 작성자 ID
    private int qReplyCount; // 댓글 개수 카운팅

    // JsonFormat = JSON 형태로 받아올 시 공백이 배열로 처리됨.
    // 그래서 JSON 형태로 받아올 때 받아올 형태를 명시해 줌
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate; // 리플 작성일자

    @JsonIgnore
    private LocalDateTime modDate; // 리플 수정일자
}
