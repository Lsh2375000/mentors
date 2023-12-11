package kr.nomadlab.progrow_project.myPage.controller;


import kr.nomadlab.progrow_project.board.dto.BoardDTO;
import kr.nomadlab.progrow_project.member.dto.MemberSecurityDTO;
import kr.nomadlab.progrow_project.member.dto.MentorDTO;
import kr.nomadlab.progrow_project.mentoring.dto.MentoringDTO;
import kr.nomadlab.progrow_project.boardQna.dto.QBoardDTO;
import kr.nomadlab.progrow_project.board.service.BoardService;
import kr.nomadlab.progrow_project.member.service.MenteeService;
import kr.nomadlab.progrow_project.member.service.MentorService;
import kr.nomadlab.progrow_project.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final MentoringService mentoringService;
    private final BoardService boardService;
    private final MentorService mentorService;
    private final MenteeService menteeService;

    /*멘토 마이페이지 시작*/
    @GetMapping("/mentor")
    public String mentorMyPageGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO ,Model model) {
        log.info("mentorMyPageGET() ...");
//        mentoringService.getOne();
//        log.info(memberSecurityDTO.getType());

        MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMid());
        log.info(mentorDTO);
        String mentor_id = memberSecurityDTO.getMid();
        List<MentoringDTO> dtoList = mentoringService.mentorMentoring(mentor_id);
        model.addAttribute("mentoringList", dtoList);

        if (!memberSecurityDTO.getType().equals("tor")) {
            return "redirect:/";
        }
        return "/mypage/mentor";
    }

    @PostMapping("/mentor")
    public void mentorMyPagePOST() {
        log.info("mentorMyPagePOST() ...");
    }
    /*멘토 마이페이지 끝*/
    /*멘티 마이페이지 시작*/
    @GetMapping("/mentee")
    public String menteeMyPageGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model) { // 멘티 마이페이지 GET
        log.info("menteeMyPageGET() ...");

        String mentee_id = memberSecurityDTO.getMid();
        List<MentoringDTO> dtoList = mentoringService.menteeMentoring(mentee_id);
        model.addAttribute("mentoringList", dtoList);

//        log.info(memberSecurityDTO.getType());
        if (!memberSecurityDTO.getType().equals("tee")) {
            return "redirect:/";
        }
        return "/mypage/mentee";
    }

    @PostMapping("/mentee")
    public void menteeMyPagePOST() {
        log.info("menteeMyPagePOST() ...");
    }
    /*멘티 마이페이지 끝*/


    @GetMapping("/saveData") // 저장한 자료 목록
    public void saveDataGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, MentoringDTO mentoringDTO) {
        log.info("Save Data GET()...");
    }
    @GetMapping("/answer") // 멘토가 작성한 답변 목록
    public void answerGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, QBoardDTO qBoardDTO) {
        log.info("Answer GET()...");
    }
    @GetMapping("/question") // 멘티가 작성한 질문 목록
    public void questionGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, QBoardDTO qBoardDTO) {
        log.info("Question GET()...");
    }
    @GetMapping("/write") // 유저가 작성한 게시글 목록
    public void   writeGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model) {
        
        log.info("writeGET() ...");
        String mid = memberSecurityDTO.getMid();
        List<BoardDTO> dtoList = boardService.myPage(mid);
        model.addAttribute("boardList", dtoList);
        String type = memberSecurityDTO.getType();

    }





}
