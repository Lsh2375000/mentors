package kr.nomadlab.mentors.chat.mapper;

import kr.nomadlab.mentors.chat.dto.ChatListDTO;
import kr.nomadlab.mentors.chat.dto.ChatRoomDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class ChatRoomMapperTest {
    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Test
    void insertChatRoomTest() {
        Long mno = 2L; // 채팅방 생성하는 회원 고유번호
        String name = "createChatTest"; // 채팅방 이름
        int maxMembers = 8;
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.create(mno, name, maxMembers); // 채팅방 생성
        chatRoomMapper.insertChatRoom(chatRoomDTO);
    }

    @Test
    void insertChatListTest() {
        Long mno = 1L;
        ChatListDTO chatListDTO = ChatListDTO.builder()
                .roomId("c75f1316-89d9-4749-b92a-e1ccc936d402")
                .mno(mno)
                .role("LEADER")
                .build();
        chatRoomMapper.insertChatList(chatListDTO);
    }

    @Test
    void selectRoomListTest() {
        Long mno = 1L;
        List<ChatRoomDTO> chatRoomDTOList = chatRoomMapper.selectRoomList(mno);
        chatRoomDTOList.forEach(log::info);
    }

    @Test
    void selectRoomByIdTest() {
        String roomId = "c75f1316-89d9-4749-b92a-e1ccc936d402";
        ChatRoomDTO chatRoomDTO = chatRoomMapper.selectRoomById(roomId);
        log.info(chatRoomDTO);
    }
}