package kr.nomadlab.mentors.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTagDTO {
    private Long htNo; // 해쉬태그 고유번호
    private Long projectNo; // 게시글 고유번호
    private String tagName; // 태그 이름
    private Long tagCount; // 태그 수
}
