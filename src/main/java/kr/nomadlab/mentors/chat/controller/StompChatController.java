package kr.nomadlab.mentors.chat.controller;

import kr.nomadlab.mentors.chat.dto.ChatMessageDTO;
import kr.nomadlab.mentors.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template; // 특정 Broker로 메세지를 전달
    private final Map<String, String> map = new HashMap<>();
    private final ChatService chatService;

    // Client가 SEND할 수 있는 경로
    // stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    // "/pub/chat/enter"
    @MessageMapping("/chat/enter") // WebSocket으로 들어오는 메세지 발행 처리
    public void enter(ChatMessageDTO message) {
        List<String> liveUser = new ArrayList<>();
        message.setMessage(message.getSender() + "님이 채팅방에 참여하였습니다.");

        map.put(message.getSender(), message.getRoomId());
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getValue().equals(message.getRoomId()) ){
                liveUser.add(entry.getKey());
            }
        }
        message.setUserList(liveUser);
        message.setState(0); // 입장하거나 채팅방 활동시

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/out")
    public void out(ChatMessageDTO message){
        message.setMessage(message.getSender() + "님이 채팅방에 나가셨습니다.");

        List<String> liveUser = new ArrayList<>();
        map.remove(message.getSender());
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getValue().equals(message.getRoomId()) ){
                liveUser.add(entry.getKey());
            }
        }
        message.setUserList(liveUser);
        message.setState(1); // 퇴장시
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessageDTO message) {

        // 메세지를 db에 저장
        message.setSendTime(LocalDateTime.now());
        chatService.sendMessage(message);

        List<String> liveUser = new ArrayList<>();
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getValue().equals(message.getRoomId()) ){
                liveUser.add(entry.getKey());
            }
        }
        message.setUserList(liveUser);
        message.setState(0); // 입장하거나 채팅방 활동시

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
