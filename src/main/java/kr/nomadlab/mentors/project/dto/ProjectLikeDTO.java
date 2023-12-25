package kr.nomadlab.mentors.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectLikeDTO {
    
    private Long pjlNo; // 좋아요 고유번호
    
    @NotNull
    private Long projectNo; // 게시글 고유번호

    @NotNull
    private Long mno; // 회원 고유번호
}
