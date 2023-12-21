package kr.nomadlab.mentors.chat.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private Long cmNo; // 메세지 고유번호
    private Long mno; // 회원 고유번호
    private String roomId; // 채팅방 아이디
    private String sender; // 메세지 보낸 사람
    private String message; // 채팅 메세지
    private LocalDateTime sendTime; // 채팅 보낸 시간
}
