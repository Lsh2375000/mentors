package kr.nomadlab.mentors.chat.mapper;

import kr.nomadlab.mentors.chat.dto.ChatMessageDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Log4j2
class ChatMessageMapperTest {
    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Test
    void insertMessage() {
        Long mno = 1L;
        String roomId = "c75f1316-89d9-4749-b92a-e1ccc936d402";
        ChatMessageDTO chatMessageDTO = ChatMessageDTO.builder()
                .mno(mno)
                .message("메세지 테스트")
                .sender("보낸사람 테스트")
                .roomId(roomId)
                .sendTime(LocalDateTime.now())
                .build();
        chatMessageMapper.insertMessage(chatMessageDTO);
    }

    @Test
    void selectMessageList() {
        String roomId = "c75f1316-89d9-4749-b92a-e1ccc936d402";
        List<ChatMessageDTO> messageDTOList = chatMessageMapper.selectMessageList(roomId);
        messageDTOList.forEach(log::info);
    }

    @Test
    void selectMessage() {
        Long cmNo = 1L;
        ChatMessageDTO chatMessageDTO = chatMessageMapper.selectMessage(cmNo);
        log.info(chatMessageDTO);
    }
}