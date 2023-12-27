package kr.nomadlab.mentors.project.domain;

import kr.nomadlab.mentors.board.domain.HashTagVO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectVO {
    private Long projectNo; // 게시글 고유번호
    private Long mno; // 회원 고유번호
    private String writer; // 회원 닉네임
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private Long hit; // 게시글 조회수
    private LocalDateTime addDate; // 게시글 등록 날짜
    private Long replyCount; // 게시글 댓글 수
    private Long likeCount; // 게시글 좋아요 수

    private List<ProjectTagVO> tagVOList = new ArrayList<>(); // 게시글 해쉬태그 목록
} 
