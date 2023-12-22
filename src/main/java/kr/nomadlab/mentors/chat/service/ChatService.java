package kr.nomadlab.mentors.chat.service;

import kr.nomadlab.mentors.chat.dto.ChatMessageDTO;
import kr.nomadlab.mentors.chat.dto.ChatRoomDTO;

import java.util.List;

public interface ChatService {
    ChatMessageDTO sendMessage(ChatMessageDTO messageDTO); // 보낸 메세지
    public List<ChatMessageDTO> getMessages(String roomId); // 메세지 조회
    public ChatRoomDTO createChatRoom(Long mno, String name, int maxMembers); // 채팅방 생성
    public void inviteChatRoom(Long mno, String roomId); // 채팅방 초대
    public List<ChatRoomDTO> getRoomList(Long mno); // // 채팅 목록 조회
    public ChatRoomDTO getRoom(String roomId); // 채팅방 아이디와 일치하는 채팅방 조회
}