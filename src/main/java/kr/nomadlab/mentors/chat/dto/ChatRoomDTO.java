package kr.nomadlab.mentors.chat.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
public class ChatRoomDTO {
    private String roomId; // 채팅방 아이디
    private String name; // 채팅방 이름
    private Long mno; // 채팅방 방장 고유번호
    private int currentMembers; // 현재 인원 수
    private int maxMembers; // 최대 인원 수
    private String lastMessage; // 마지막 메세지
    private LocalDateTime lastMessageTime;// 마지막 메세지 보낸 시간

    public static ChatRoomDTO create(Long mno, String name, int maxMembers) { // 채팅방 생성
        ChatRoomDTO room  = new ChatRoomDTO();

        room.mno = mno;
        room.roomId = UUID.randomUUID().toString(); // uuid 생성해서 채팅방 아이디로
        room.name = name; // 채팅방 이름
        room.maxMembers = maxMembers; // 채팅방 최대 인원

        return room;
    }

}
