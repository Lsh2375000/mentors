package kr.nomadlab.progrow_project.mentoring;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentorRoomNoticeVO {
    Long num; // 공지 번호
    String mentor_nickname; // 멘토 id;
    String title; // 제목
    String content; // 내용
    private LocalDateTime addDate; // 등록시간

}
