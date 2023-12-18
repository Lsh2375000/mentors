package kr.nomadlab.mentors.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.board.service.BoardService;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){ // 게시글 전체 목록 페이지
        log.info("/board/list(GET)...");
        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.getBoardList(pageRequestDTO);
        model.addAttribute("responseDTO", pageResponseDTO);
    }

//    @PreAuthorize("isAuthenticated()") //로그인한 시용자만
    @GetMapping("/register")
    public void register(){ // 게시글 등록 페이지
        log.info("/board/register(GET)...");
    }

    @PostMapping("/register")
    public String registerPOST(BoardDTO boardDTO){ // 게시글 등록
        log.info("/board/register(POST)...");
        log.info(boardDTO);
        boardDTO.setMemberId("testId");

        // file (현재는 임시폴더 Temporary 위치에 저장됨)를 읽어서, 원하는 위치(디렉토리)에 저장(파일 복사)
        // +@ 디렉토리권한, 파일권한 나(owner) 7, 그룹(group) 7, 다른사람(guest/browser) 5
             /* 데이터가 저장된 후에 이미지 파일 이동*/
        //List<String> fileName = Collections.singletonList(boardDTO.getFileNames());
//        String fileNames = boardDTO.getFileNames();
//        moveFile(fileNames); //tmp에 있는 s_로 시작하는 파일을 찾는다.
        
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
