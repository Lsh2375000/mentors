package kr.nomadlab.mentors.project.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectTagVO {
    private Long htNo; // 해쉬태그 고유번호
    private Long projectNo; // 게시글 고유번호
    private String tagName; // 태그 이름
}
