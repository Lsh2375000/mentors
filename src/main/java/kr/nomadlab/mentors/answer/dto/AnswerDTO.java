package kr.nomadlab.mentors.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    private Long ano; // 답변 고유 번호
    private Long parentNo; // 부모 답변 번호
    private Long mno; // 회원 고유 번호
    private Long qno; // 질문 고유 번호
    private String content; // 답변 내용
    private String writer; // 답변 작성자
    private LocalDateTime addDate; // 답변 작성 날짜

    @Builder.Default
    private boolean cmEdtNot = true; // 답변 수정 유무
    @Builder.Default
    private boolean isSelect = false; // 답변 채택 유무
    private String title; // 질문 제목

    private List<AnswerDTO> children = new ArrayList<>();
}
