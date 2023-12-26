package kr.nomadlab.mentors.project.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectLikeVO {

    private Long pjlNo; // 좋아요 고유번호
    private Long projectNo; // 게시글 고유번호
    private Long mno; // 회원 고유번호
}
