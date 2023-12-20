package kr.nomadlab.mentors.main.controller;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.main.dto.MainDTO;
import kr.nomadlab.mentors.main.service.MainService;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
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
@RequestMapping({"/main", "/"})
@Log4j2
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("")
    public String main(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO) {
        pageRequestDTO.setSize(20);
        PageResponseDTO<MainDTO> mainList = mainService.list(pageRequestDTO);

        log.info("스킵 "+pageRequestDTO.getSkip());
        log.info("사이즈 "+pageRequestDTO.getSize());

        String keyword = pageRequestDTO.getKeyword();
        String sort = pageRequestDTO.getSort();
        List<String> language = pageRequestDTO.getLanguage();
        String paidFree = pageRequestDTO.getPaidFree();

        if(memberSecurityDTO != null){
            Long mno = memberSecurityDTO.getMno();
            boolean isMentoring = mainService.isMentoring(mno);
            model.addAttribute("isMentoring", isMentoring);
            log.info("멘토링 여부 /? " + isMentoring);
        }


        log.info("키워드 ? " + keyword);
        log.info("언어 ? "+ language);
        log.info("무료/유료 ? " + paidFree);
        log.info("인기순/최신순 ? " + sort);

        if(language != null){
            model.addAttribute("language", language);
        }

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

        model.addAttribute("mentoringCnt", mentoringCnt);
    }

    @PostMapping("/write")
    public String write(MainDTO mainDTO){ // 글등록
        mainService.register(mainDTO);

        return "redirect:/main";
    }


}
