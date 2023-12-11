package kr.nomadlab.progrow_project.mentoring;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeReplyDTO {
    private Long rno; // 리플 고유번호

    @NotNull
    private Long num; // 공지사항의 글 고유번호

    @NotEmpty
    private String replyText; // 리플 내용

    @NotEmpty
    private String replyWriter; // 리플 작성자

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate; // 리플 작성일자

}
