package kr.nomadlab.progrow_project.boardReply.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.nomadlab.progrow_project.boardReply.dto.ReplyDTO;
import kr.nomadlab.progrow_project.boardReply.service.ReplyService;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    //댓글 등록

    @Operation(summary = "Replies POST", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {
        log.info(replyDTO);

        if(bindingResult.hasErrors()){
            throw  new BindException((bindingResult));
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long rno = replyService.register(replyDTO);

        resultMap.put("rno", rno);
        return resultMap;
    }
    // 특정 게시물의 댓글 목록
    @Operation(summary = "boardReply", description = "GET 방식으로 게시물의 댓글 목록을 보여줌")
    @GetMapping(value = "/list/{boardNo}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("boardNo")int boardNo, PageRequestDTO pageRequestDTO){
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getList(boardNo, pageRequestDTO);
        return responseDTO;
    }

    //특정 댓글 조회
    @Operation(summary = "Read reply", description = "GET 방식으로 특정 댓글 조회")
    @GetMapping(value = "/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno){
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }


    @Operation(summary = "Reply Modify", description = "PUT 방식으로 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO){
        replyDTO.setRno(rno);
        replyService.modify(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }



    @Operation(summary = "Delete reply", description = "Delete 방식으로 댓글 삭제")
    @DeleteMapping(value = "/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno){
        replyService.remove(rno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }






}
