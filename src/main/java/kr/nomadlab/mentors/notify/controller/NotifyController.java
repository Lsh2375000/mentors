package kr.nomadlab.mentors.notify.controller;

import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.notify.dto.NotifyDto;
import kr.nomadlab.mentors.notify.service.NotifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notify")
@Log4j2
@RequiredArgsConstructor // 의존성 주입 위해
public class NotifyController {
    private final NotifyService notifyService;

    //메세지 저장
    @PutMapping("/repository/{types}")
    public ResponseEntity<String> sendNotify(@AuthenticationPrincipal MemberSecurityDTO member, @PathVariable String types){
        log.info("this is repository/types");
        notifyService.addNotify(member.getMno(), types);

        return ResponseEntity.ok("Request processed successfully");
    }
    //알림 메세지 보내기
    @PostMapping("/repository")
    public ResponseEntity<String> passNotify(@AuthenticationPrincipal MemberSecurityDTO member, @RequestBody NotifyDto notifyDto){
        log.info("this is repository/passNotify");
        notifyService.passNotify(notifyDto.getTypes(), notifyDto.getReceiverMno(), member.getNickname());

        return ResponseEntity.ok("Request passMessage successfully");
    }


    @GetMapping("/count")
    public int countNotify(@AuthenticationPrincipal MemberSecurityDTO member){
        log.info("this is notify count");
        int count = notifyService.countNotify(member.getMno());

        return count;
    }

    //notifyCnt 초기화
    @PutMapping("/readNotify")
    public void readNotify(@AuthenticationPrincipal MemberSecurityDTO member){
        log.info("this is notify resetCnt");

        notifyService.readNotify(member.getMno());
    }

    //읽지 않은 알림 가져오기
    @GetMapping("/getNewNotify")
    public List<NotifyDto> getNotReadNotify(@AuthenticationPrincipal MemberSecurityDTO member){
        log.info("this is notify getNewNotify");
        List<NotifyDto> notifyDto = notifyService.getNotReadNotify(member.getMno());

        return notifyDto;
    }
}
