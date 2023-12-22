package kr.nomadlab.mentors.chat.mapper;

import kr.nomadlab.mentors.chat.dto.ChatListDTO;
import kr.nomadlab.mentors.chat.dto.ChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ChatRoomMapper {
    public void insertChatRoom(ChatRoomDTO chatRoomDTO); // 채팅방 생성

    public void insertChatList(ChatListDTO chatListDTO); // 채팅 목록 추가

    public List<ChatRoomDTO> selectRoomList(Long mno); // 해당 회원의 채팅목록 조회

    public ChatRoomDTO selectRoomById(String roomId); // 채팅방 조회

}
