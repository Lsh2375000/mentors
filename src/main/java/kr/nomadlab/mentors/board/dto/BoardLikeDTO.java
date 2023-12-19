package kr.nomadlab.mentors.board.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardLikeDTO {
    
    private Long blNo; // 좋아요 고유번호
    
    @NotNull
    private Long boardNo; // 게시글 고유번호

    @NotNull
    private Long mno; // 회원 고유번호
}
