package kr.nomadlab.mentors.chat.service;

import kr.nomadlab.mentors.chat.dto.ChatRoomDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ChatServiceImplTest {
    @Autowired
    private ChatService chatService;

    @Test
    void getRoomListTest() {
        List<ChatRoomDTO> chatRoomDTOList = chatService.getRoomList(2L);
        chatRoomDTOList.forEach(log::info);
    }
}