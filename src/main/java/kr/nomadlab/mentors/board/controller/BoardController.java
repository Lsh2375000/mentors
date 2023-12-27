package kr.nomadlab.mentors.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.board.service.BoardService;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){ // 게시글 전체 목록 페이지
        log.info("/board/list(GET)...");
        log.info("keyword: " + pageRequestDTO.getKeyword());
        log.info("hashTag: " + pageRequestDTO.getHashTag());

        // 키워드가 존재하면
        String keyword = pageRequestDTO.getKeyword();
        if (keyword != null && !keyword.isEmpty()) {
            pageRequestDTO.setType("twc");
        }

        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.getBoardList(pageRequestDTO);
        List<HashTagDTO> topTagList = boardService.getTopTagList();

        model.addAttribute("responseDTO", pageResponseDTO);
        model.addAttribute("topTagList", topTagList);
    }

//    @PreAuthorize("isAuthenticated()") //로그인한 시용자만
    @GetMapping("/register")
    public void register(){ // 게시글 등록 페이지
        log.info("/board/register(GET)...");
    }

    @PostMapping("/register")
    public String registerPOST(BoardDTO boardDTO, @RequestParam("hashTags") String[] hashTags){ // 게시글 등록
        log.info("/board/register(POST)...");
        log.info(boardDTO);

        if (hashTags != null) { // 태그가 존재할 경우
            log.info("Hash Tags: " + Arrays.toString(hashTags));
            List<HashTagDTO> tagList = new ArrayList<>();
            Arrays.asList(hashTags).forEach(tag -> tagList.add(HashTagDTO.builder()
                    .tagName(tag)
                    .build()));
            boardDTO.setTagList(tagList);
        } else {
            log.info("No Hash Tags provided.");
        }

        boardDTO.setMno(1L);
        
        boardService.registerBoard(boardDTO);

        return "redirect:/board/list";
    }

    
//    @PreAuthorize("isAuthenticated()") //로그인한 시용자만
    @GetMapping({"/view", "/modify"}) // 게시글 상세 페이지, 수정 페이지
    public void view(@RequestParam("boardNo") Long boardNo, PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request){
        log.info("boardNo: " + boardNo );
        String requestedUrl  = request.getRequestURI(); // /board/read, //board/modify
        log.info("requestedUrl: " + requestedUrl);

        BoardDTO boardDTO = null;
        if(requestedUrl.equals("/board/view")){
            boardDTO = boardService.getBoard(boardNo, "view"); // 조회수 증가 + read 페이지로 이동
        } else {
            boardDTO = boardService.getBoard(boardNo, "modify"); //  modify 페이지로 이동
        }

        model.addAttribute("board", boardDTO);
    }


//    @PreAuthorize("principal.username == #boardDTO.memberId") //로그인 정보와 전달 받은 boardDTO nickname가 같다면 수정 가능
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){ // 게시글 수정
        log.info("/board/modify(POST)...");
        log.info(boardDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors....");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("boardNo", boardDTO.getBoardNo());
            return "redirect:/board/modify?" + link;
        }

        boardService.modifyBoard(boardDTO);

        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("boardNo", boardDTO.getBoardNo());

        return "redirect:/board/view";
    }

//    @PreAuthorize("principal.username == #boardDTO.memberId") //로그인 정보와 전달 받은 boardDTO nickname 가 같다면 삭제 가능
    @PostMapping("/remove")
    public String removeOne(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        log.info("/board/remove(POST)...");
        Long boardNo = boardDTO.getBoardNo();
        log.info("boardNo: " + boardNo);

        boardService.removeBoard(boardNo);

        return "redirect:/board/list";
    }

    /* 파일 이름을 받아서 이동하는 기능의 메서드 */
    private void moveFile(String fileNames){
        /* 등록시에 첨부 파일을 이동*/
//        log.info("moveFile : " + fileNames);
//        String srcPath = uploadTmpPath + File.separator + fileNames;
//         String destPath = uploadRealPath + File.separator + fileNames;
//        try{
//            FileInputStream fileInputStream = new FileInputStream(srcPath);
//            FileOutputStream fileOutputStream = new FileOutputStream(destPath);
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//
//            int i;
//            while (( i = bufferedInputStream.read()) != -1){ //파일 끝까지 읽기
//                bufferedOutputStream.write(i); //읽은 만큼 쓰기
//            }
//            bufferedInputStream.close();
//            bufferedOutputStream.close();
//            fileInputStream.close();
//            fileOutputStream.close();
//
//            Files.delete(Paths.get(uploadTmpPath + File.separator + fileNames));
//        } catch (IOException e){
//            e.printStackTrace();
//        }
    }


}
