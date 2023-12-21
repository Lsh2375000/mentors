package kr.nomadlab.mentors.chat.dto;

import lombok.*;

import java.util.HashSet;
import java.util.UUID;

@Data
public class ChatRoomDTO {
    private String roomId; // 채팅방 아이디
    private String name; // 채팅방 이름
    private Long mno; // 채팅방 방장 고유번호
    private int currentMembers; // 현재 인원 수
    private int maxMembers; // 최대 인원 수

//    private Set<WebSocketSession> sessionSet = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    public static ChatRoomDTO create(Long mno, String name, int maxMembers) { // 채팅방 생성
        ChatRoomDTO room  = new ChatRoomDTO();

        room.mno = mno;
        room.roomId = UUID.randomUUID().toString(); // uuid 생성해서 채팅방 아이디로
        room.name = name; // 채팅방 이름
        room.maxMembers = maxMembers; // 채팅방 최대 인원

        return room;
    }
}
