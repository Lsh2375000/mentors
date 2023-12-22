package kr.nomadlab.mentors.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatListDTO {
    private Long clNo; // 채팅목록 고유번호
    private Long mno; // 채팅에 참여한 회원 고유번호
    private String roomId; // 채팅방 아이디
    private String role; // LEADER OR MEMBER
}
