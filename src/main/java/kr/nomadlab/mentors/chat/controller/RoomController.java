package kr.nomadlab.mentors.chat.controller;

import kr.nomadlab.mentors.chat.dto.ChatRoomDTO;
import kr.nomadlab.mentors.chat.service.ChatService;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
@Log4j2
public class RoomController {
    private final ChatService chatService;

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public ModelAndView rooms(@AuthenticationPrincipal MemberSecurityDTO member) {
        log.info("# All Chat Rooms");
        ModelAndView modelAndView = new ModelAndView("chat/rooms");

        modelAndView.addObject("list", chatService.getRoomList(member.getMno()));
        log.info("/rooms(GET)....");

        return modelAndView;
    }

    // 채팅방 개설
    @PostMapping("/room")
    public String create(@RequestParam("name") String name,
                         @RequestParam("maxMembers") int maxMembers,
                         @AuthenticationPrincipal MemberSecurityDTO member, RedirectAttributes redirectAttributes) {
        log.info("# Create Chat Room...");

        Long mno = member.getMno();

        ChatRoomDTO chatRoomDTO = chatService.createChatRoom(mno, name, maxMembers);
        log.info(chatRoomDTO);

        redirectAttributes.addAttribute("roomId", chatRoomDTO.getRoomId());

        return "redirect:/chat/rooms";
    }
    
    // 채팅방 조회
    @GetMapping("/room")
    public void getRoom(@RequestParam("roomId") String roomId, Model model) {
        log.info("# get Chat Room, roomID : " + roomId);
        model.addAttribute("messages", chatService.getMessages(roomId));
        model.addAttribute("room", chatService.getRoom(roomId));
    }
}
