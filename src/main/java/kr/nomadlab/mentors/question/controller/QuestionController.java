package kr.nomadlab.mentors.question.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.board.service.BoardService;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.question.dto.QuestionDTO;
import kr.nomadlab.mentors.question.dto.QuestionTagDTO;
import kr.nomadlab.mentors.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/question")
@Log4j2
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){ // 게시글 전체 목록 페이지
        log.info("/question/list(GET)...");
        log.info("keyword: " + pageRequestDTO.getKeyword());
        log.info("hashTag: " + pageRequestDTO.getHashTag());

        // 키워드가 존재하면
        String keyword = pageRequestDTO.getKeyword();
        if (keyword != null && !keyword.isEmpty()) {
            pageRequestDTO.setType("twc");
        }

        PageResponseDTO<QuestionDTO> pageResponseDTO = questionService.getQuestionList(pageRequestDTO);
        List<QuestionTagDTO> topTagList = questionService.getTopTagList();

        model.addAttribute("responseDTO", pageResponseDTO);
        model.addAttribute("topTagList", topTagList);
    }

//    @PreAuthorize("isAuthenticated()") //로그인한 시용자만
    @GetMapping("/register")
    public void register(){ // 게시글 등록 페이지
        log.info("/question/register(GET)...");
    }

    @PostMapping("/register")
    public String registerPOST(QuestionDTO questionDTO,
                               @RequestParam("hashTags") String[] hashTags,
                               @AuthenticationPrincipal MemberSecurityDTO member){ // 게시글 등록
        log.info("/question/register(POST)...");
        log.info(questionDTO);

        if (hashTags != null) { // 태그가 존재할 경우
            log.info("Hash Tags: " + Arrays.toString(hashTags));
            List<QuestionTagDTO> tagList = new ArrayList<>();
            Arrays.asList(hashTags).forEach(tag -> tagList.add(QuestionTagDTO.builder()
                    .tagName(tag)
                    .build()));
            questionDTO.setTagList(tagList);
        } else {
            log.info("No Hash Tags provided.");
        }

        questionDTO.setMno(member.getMno());
        
        questionService.registerQuestion(questionDTO);

        return "redirect:/question/list";
    }

    
//    @PreAuthorize("isAuthenticated()") //로그인한 시용자만
    @GetMapping({"/view", "/modify"}) // 게시글 상세 페이지, 수정 페이지
    public void view(@RequestParam("qno") Long qno, PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request){
        log.info("qno: " + qno );
        String requestedUrl  = request.getRequestURI(); // /board/read, //board/modify
        log.info("requestedUrl: " + requestedUrl);

        QuestionDTO questionDTO = null;
        if(requestedUrl.equals("/question/view")){
            questionDTO = questionService.getQuestion(qno, "view"); // 조회수 증가 + read 페이지로 이동
        } else {
            questionDTO = questionService.getQuestion(qno, "modify"); //  modify 페이지로 이동
        }

        model.addAttribute("question", questionDTO);
    }


//    @PreAuthorize("principal.username == #boardDTO.memberId") //로그인 정보와 전달 받은 boardDTO nickname가 같다면 수정 가능
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid QuestionDTO questionDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){ // 게시글 수정
        log.info("/question/modify(POST)...");
        log.info(questionDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors....");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("qno", questionDTO.getQno());
            return "redirect:/question/modify?" + link;
        }

        questionService.modifyQuestion(questionDTO);

        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("qno", questionDTO.getQno());

        return "redirect:/question/view";
    }

//    @PreAuthorize("principal.username == #boardDTO.memberId") //로그인 정보와 전달 받은 boardDTO nickname 가 같다면 삭제 가능
    @PostMapping("/remove")
    public String removeOne(QuestionDTO questionDTO, RedirectAttributes redirectAttributes){
        log.info("/question/remove(POST)...");
        Long qno = questionDTO.getQno();
        log.info("qno: " + qno);

        questionService.removeQuestion(qno);

        return "redirect:/question/list";
    }
}
