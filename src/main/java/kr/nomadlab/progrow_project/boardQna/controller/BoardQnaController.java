package kr.nomadlab.progrow_project.boardQna.controller;

import kr.nomadlab.progrow_project.boardQna.dto.QBoardDTO;
import kr.nomadlab.progrow_project.boardQna.service.QBoardService;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/qboard")
@Log4j2
@RequiredArgsConstructor
public class BoardQnaController {
    private final QBoardService qBoardService;

    @GetMapping("/list")
    public void qBoardList(@Valid PageRequestDTO pageRequestDTO, Long qnaBoardNo, BindingResult bindingResult, Model model) {

        log.info(pageRequestDTO);

        if (bindingResult.hasErrors()) { // @Valid 를 이용해서 잘못된 파라미터 값들이 들어오면 page는 1, size는 10으로 고정된 값을 처리
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        PageResponseDTO pageResponseDTO = qBoardService.getList(pageRequestDTO);
        log.info(pageResponseDTO);

        model.addAttribute("responseDTO", pageResponseDTO);
    }

    @GetMapping("/register")
    public void add(){
        log.info("/qboard/register...");
    }

    @PostMapping("/register")
    public String addPost(QBoardDTO qBoardDTO, HttpServletRequest request){
        log.info("/qboard/register POST...");
        log.info(qBoardDTO);
        qBoardService.add(qBoardDTO);
        return "redirect:/qboard/list";
    }

    @GetMapping({"/read", "modify"})
    public void view(Long qnaBoardNo, PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request) {
        log.info("/qboard/read...");
        String requestedUrl = request.getRequestURI();
        log.info(requestedUrl);

        QBoardDTO qBoardDTO = null;
        if (requestedUrl.equals("/qboard/read")) {
            qBoardDTO = qBoardService.getOne(qnaBoardNo, "read");
        } else {
            qBoardDTO = qBoardService.getOne(qnaBoardNo, "modify");
        }

        model.addAttribute("dto", qBoardDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid QBoardDTO qBoardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("qboard modify post....." + qBoardDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors....");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("qnaBoardNo", qBoardDTO.getQnaBoardNo());
            return "redirect:/board/modify? + link";
        }

        qBoardService.modifyOne(qBoardDTO);

        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("qnaBoardNo", qBoardDTO.getQnaBoardNo());

        return "redirect:/qboard/read";
    }


    @PostMapping("/remove")
    public String remove(QBoardDTO qBoardDTO, RedirectAttributes redirectAttributes){
        Long qnaBoardNo = qBoardDTO.getQnaBoardNo();
        log.info("qBoard remove post" + qnaBoardNo);

        qBoardService.deleteOne(qnaBoardNo);
        log.info(qBoardDTO.getQnaBoardNo());
        return "redirect:/qboard/list";

    }






}
