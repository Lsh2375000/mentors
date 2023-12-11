package kr.nomadlab.progrow_project.boardQna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QBoardImageDTO {
    private String uuid;
    private String fileName;
    private int ord;
}
