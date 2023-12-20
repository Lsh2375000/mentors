package kr.nomadlab.mentors.member.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentorApplyVO {
    private Long mno;
    private String devLanguage;
    private String fileNames;
}
