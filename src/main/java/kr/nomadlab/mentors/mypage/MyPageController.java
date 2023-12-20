package kr.nomadlab.mentors.mypage;



import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.main.dto.MainDTO;
import kr.nomadlab.mentors.main.service.MainService;
import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.dto.MenteeDTO;
import kr.nomadlab.mentors.member.dto.MentorDTO;

import kr.nomadlab.mentors.member.service.MemberService;
import kr.nomadlab.mentors.member.service.MenteeService;
import kr.nomadlab.mentors.member.service.MentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;
    private final MentorService mentorService;
    private final MenteeService menteeService;
    private final MainService mainService;

    /*멘토 프로필 시작*/
    @GetMapping("/mentorProfile")
    public void mentorMyPageGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model, String nickname) {
        log.info("mentorProfile GET...");

        MemberDTO memberDTO = memberService.getProfileNickname(nickname);
        log.info("memberDTO : " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        if (memberSecurityDTO != null) {
            if (memberSecurityDTO.getNickname().equals(nickname)) {
                MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMemberId());
                log.info("mentorDTO : " + mentorDTO);
                model.addAttribute("mentorDTO", mentorDTO);
            }
        }


    }
    /*멘토 프로필 끝*/
    /*멘티 프로필 시작*/
    @GetMapping("/menteeProfile")
    public void menteeMyPageGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model, String nickname) { // 멘티 마이페이지 GET
        log.info("mentorProfile GET...");

        MemberDTO memberDTO = memberService.getProfileNickname(nickname);
        log.info("memberDTO : " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        if(memberSecurityDTO != null) {
            if (memberSecurityDTO.getNickname().equals(nickname)) {
                MenteeDTO menteeDTO = menteeService.getOne(memberSecurityDTO.getMemberId());
                log.info("menteeDTO : " + menteeDTO);
                model.addAttribute("menteeDTO", menteeDTO);
            }
        }

    }

    /*멘티 프로필 끝*/

    
    @GetMapping("/mainList")// 내가 작성한 멘토링 목록 보기
    public void mainList(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){
        pageRequestDTO.setSize(12);
        PageResponseDTO<MainDTO> mainList = mainService.myPageList(pageRequestDTO, memberSecurityDTO.getMno());
        log.info("end는 ?"+mainList.getEnd());
        model.addAttribute("mainList", mainList);
    }

    @GetMapping("/mainList/remove") // 내가 작성한 멘토링 삭제
    public String removeMain(Long mbNo){
        mainService.removeOne(mbNo);

        return "redirect:/mypage/mainList";
    }

    @GetMapping("/mainList/modify") // 내가 작성한 멘토링 수정페이지
    public String modifyMain(Model model, Long mbNo, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){
        MainDTO mainDTO = mainService.getBoard(mbNo);
        Long mno = memberSecurityDTO.getMno();
        int mentoringCnt = mainService.mentoringCnt(mno);

        model.addAttribute("mentoringCnt", mentoringCnt);
        model.addAttribute("mainDTO", mainDTO);
        return "/mypage/mainModify";
    }

    @PostMapping("/mainList/modify")
    public String modifyMain(MainDTO mainDTO){
        log.info("수정 DTO ? " + mainDTO);
        mainService.modifyBoard(mainDTO);
        return "redirect:/mypage/mainList";
    }

    @GetMapping("/paymentsHistory")
    public String paymentHistory(){
        return "/mypage/paymentsHistory";
    }
}
