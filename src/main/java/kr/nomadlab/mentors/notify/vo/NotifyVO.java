package kr.nomadlab.mentors.notify.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotifyVO {
    private Long index; // 고유번호
    private Long receiverMno; //받는mno
    private Long sendMno;   //보내는 mno
    private String content; //내용
    private boolean isRead; //읽음
    private String types; //타입 ex)멘토링, 결제
    private Long typesNo; //타입의 고유번호
    private LocalDateTime sendDate;
}
