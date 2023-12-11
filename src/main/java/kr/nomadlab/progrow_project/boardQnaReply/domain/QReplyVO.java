package kr.nomadlab.progrow_project.boardQnaReply.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QReplyVO {
    private Long qnaRno; //리플 고유 번호
    private Long qnaBoardNo; //게시판의 글 고유번호
    private String content; //리플의 내용
    private String nickname; //리플 작성자 닉네임
    private String id; //리플 작성자 아이디
    private int qReplyCount; // 댓글 개수 카운팅

    // JsonFormat = JSON 형태로 받아올 시 공백이 배열로 처리됨.
    // 그래서 JSON 형태로 받아올 때 받아올 형태를 명시해 줌
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate; // 리플 작성일자

    @JsonIgnore
    private LocalDateTime modDate; // 리플 수정일자

    public void changeQnaText(String content){
        this.content = content;
    } // 리플 변경 시 메서드
}