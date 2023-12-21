package kr.nomadlab.mentors.chat.mapper;

import kr.nomadlab.mentors.chat.dto.ChatMessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    public void insertMessage(ChatMessageDTO chatMessageDTO); // 메세지 정보 DB에 저장
    public List<ChatMessageDTO> selectMessageList(String roomId); // 채팅방 아이디로 채팅 메시지 모두 조회
    public ChatMessageDTO selectMessage(Long cmNo); // 메세지 조회
}
