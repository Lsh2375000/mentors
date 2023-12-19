package kr.nomadlab.mentors.notify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyDto {
    private Long index;
    private Long receiverMno;
    private String content;
    private boolean isRead;
    private LocalDateTime sendDate;
}
