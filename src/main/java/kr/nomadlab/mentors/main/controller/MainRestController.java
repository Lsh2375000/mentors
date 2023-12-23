package kr.nomadlab.mentors.main.controller;

import kr.nomadlab.mentors.chat.dto.ChatListDTO;
import kr.nomadlab.mentors.chat.dto.ChatRoomDTO;
import kr.nomadlab.mentors.chat.service.ChatService;
import kr.nomadlab.mentors.main.dto.MainDTO;
import kr.nomadlab.mentors.main.service.MainService;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
@Log4j2
public class MainRestController {

    private final MainService mainService;
    private final ChatService chatService;

    @GetMapping("/{mbNo}")
    public MainDTO getOne(@PathVariable("mbNo")Long mbNo, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){
        log.info("레스트 들어옴");
        log.info(mbNo);
        MainDTO mainDTO = null;
        log.info("로그인 한 사람 ?" + memberSecurityDTO);
        if(memberSecurityDTO != null){
            mainDTO = mainService.getBoard(mbNo);
            mainDTO.setApplied(chatService.findMemberInRoom(mainDTO.getRoomId(), memberSecurityDTO.getMno()));
            log.info("신청 여부 "+ mainDTO.isApplied());
        }else{
            mainDTO = mainService.getBoard(mbNo);
        }

        return mainDTO;
    }

    @PostMapping("/chat")
    public void enterChat(@RequestBody ChatListDTO chatListDTO){
        Long mno = chatListDTO.getMno();
        String roomId = chatListDTO.getRoomId();

        chatService.inviteChatRoom(mno, roomId);
        log.info("채팅방초대됨");
        mainService.updateCurPeople(roomId);
        log.info("채팅방인원 업데이트됨");
    }
}
