package kr.nomadlab.mentors.chat.mapper;

import kr.nomadlab.mentors.chat.dto.ChatListDTO;
import kr.nomadlab.mentors.chat.dto.ChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ChatRoomMapper {
    public void insertChatRoom(ChatRoomDTO chatRoomDTO); // 채팅방 생성

    public void insertChatList(ChatListDTO chatListDTO); // 채팅 목록 추가

    public List<ChatRoomDTO> selectRoomList(Long mno); // 해당 회원의 채팅목록 조회

    public ChatRoomDTO selectRoomById(String roomId); // 채팅방 조회
    
    public List<ChatListDTO> selectChatMembers(String roomId); // 채팅방에 참여한 회원 목록 조회
    
    public Boolean findMemberInRoom(@Param("roomId") String roomId, @Param("mno") Long mno); // 채팅방에 해당 회원 존재 여부

    
    public void deleteChatMember(ChatListDTO chatListDTO); // 채팅방 나간 회원번호 삭제
    public void updateLeaderMno(String roomId); // 채팅방 방장이 나갈경우 mno를 0으로 변경

}
