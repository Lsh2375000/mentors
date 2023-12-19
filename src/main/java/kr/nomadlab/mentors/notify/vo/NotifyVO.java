package kr.nomadlab.mentors.notify.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotifyVO {
    private Long index;
    private Long receiverMno;
    private String content;
    private boolean isRead;
    private String types;
    private LocalDateTime sendDate;
}
