package kr.nomadlab.mentors.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorApplyDTO {
    private Long mno;
    private String devLanguage;
    private String fileNames;
    private String univName;
    private String major;
}
