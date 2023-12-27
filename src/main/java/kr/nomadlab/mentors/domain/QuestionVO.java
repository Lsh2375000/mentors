package kr.nomadlab.mentors.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionVO {
    private Long qno; // 질문 고유번호
    private String memberId; // 회원 아이디
    private String writer; // 회원 닉네임
    private String title; // 질문 제목
    private String content; // 질문 내용
    private Long hit; // 질문 조회수
    private LocalDateTime addDate; // 질문 등록 날짜
    private Long answerCount; // 질문 댓글 수
    private Long voteCount; // 질문 좋아요 수

    private List<QuestionTagVO> tagVOList = new ArrayList<>(); // 질문 해쉬태그 목록
} 
