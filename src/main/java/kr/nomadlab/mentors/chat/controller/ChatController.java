package kr.nomadlab.mentors.chat.controller;

import kr.nomadlab.mentors.chat.service.ChatService;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chat") // 적절한 요청 경로를 지정해주세요
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final ChatService chatService;

    // 채팅방 조회
    @GetMapping("/room")
    public ResponseEntity<Map<String, Object>> getRoom(@RequestParam("roomId") String roomId) {
        log.info("# get Chat Room, roomID : " + roomId);

        Map<String, Object> response = new HashMap<>();
        response.put("messages", chatService.getMessages(roomId));
        response.put("room", chatService.getRoom(roomId));

        return ResponseEntity.ok(response);
    }

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public ResponseEntity<Map<String, Object>> getRooms(@RequestParam("mno") Long mno) {
        log.info("# All Chat Rooms");

        Map<String, Object> response = new HashMap<>();

        log.info("채팅방 목록 조회");
        response.put("chatList", chatService.getRoomList(mno));
        log.info("채팅방 목록 조회2");

        return ResponseEntity.ok(response);
    }
}

