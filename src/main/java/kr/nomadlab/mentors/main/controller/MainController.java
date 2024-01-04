package kr.nomadlab.mentors.main.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.nomadlab.mentors.admin.dto.VisitorDTO;
import kr.nomadlab.mentors.admin.service.VisitorService;
import kr.nomadlab.mentors.chat.service.ChatService;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.main.dto.MainDTO;
import kr.nomadlab.mentors.main.service.MainService;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.dto.MentorDTO;
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

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping({"/main", "/"})
@Log4j2
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final ChatService chatService;
    private final MentorService mentorService;
    private final VisitorService visitorService;

    @GetMapping("") // 메인
    public String main(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,
                       HttpServletRequest request) {

        // 방문자 등록
        VisitorDTO visitorDTO = new VisitorDTO();
        HttpSession session = request.getSession();
        String sessionId = session.getId();

            log.info("쿼리스트링 없음");
            if(!visitorService.isSameId(sessionId)){ // 동일한 세션 Id가 없으면
                if(memberSecurityDTO != null){ // 로그인 했으면
                    visitorDTO.setLogin(true); // true
                }else{
                    visitorDTO.setLogin(false); // 로그인 안하면 false
                }
                visitorDTO.setVisitDate(LocalDate.now());
                visitorDTO.setSessionId(sessionId);
                visitorService.insertVisitor(visitorDTO);
                log.info("쿼리스트링 없어서 방문자 추가함");
            }
            pageRequestDTO.setSize(20);
            PageResponseDTO<MainDTO> mainList = mainService.list(pageRequestDTO); // 멘토링 목록
            List<MentorDTO> mentorDTOList1 = mentorService.listByRanking1(); // 1등멘토 회원 목록
            List<MentorDTO> mentorDTOList2 = mentorService.listByRanking2(); // 1등멘토 회원 목록
            List<MentorDTO> mentorDTOList3 = mentorService.listByRanking3(); // 1등멘토 회원 목록
            log.info("스킵 "+pageRequestDTO.getSkip());
            log.info("사이즈 "+pageRequestDTO.getSize());

            String keyword = pageRequestDTO.getKeyword();
            String sort = pageRequestDTO.getSort();
            List<String> language = pageRequestDTO.getLanguage();
            String paidFree = pageRequestDTO.getPaidFree();

            if(memberSecurityDTO != null){
                if(memberSecurityDTO.getAuthorities().toArray()[0].toString().equals("ROLE_MENTOR")) {
                    MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMemberId());
                    model.addAttribute("mentorDTO", mentorDTO);
                }
            }


            if(language != null){
                model.addAttribute("language", language);
            }


            model.addAttribute("mentorList1", mentorDTOList1);
            model.addAttribute("mentorList2", mentorDTOList2);
            model.addAttribute("mentorList3", mentorDTOList3);
            model.addAttribute("paidFree", paidFree);
            model.addAttribute("sort", sort);
            model.addAttribute("keyword", keyword);
            model.addAttribute("mainList", mainList);

            return "main/main";

    }


    @GetMapping("/write")
    public void write(Model model, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){ // 글쓰기페이지
        Long mno = memberSecurityDTO.getMno();
        int mentoringCnt = mainService.mentoringCnt(mno);

        MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMemberId());
        model.addAttribute("mentorDTO", mentorDTO);

        model.addAttribute("mentoringCnt", mentoringCnt);
    }

    @PostMapping("/write")
    public String write(MainDTO mainDTO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){ // 글등록
        String roomId = chatService.createChatRoom(memberSecurityDTO.getMno(), mainDTO.getTitle(), mainDTO.getMaxPeople()).getRoomId();
        mainDTO.setRoomId(roomId);
        mainService.register(mainDTO);
        mainService.trueIsMentoring(memberSecurityDTO.getMno());

        return "redirect:/main";
    }


}
