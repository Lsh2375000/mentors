package kr.nomadlab.mentors.boardReply.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.boardReply.dto.ReplyDTO;
import kr.nomadlab.mentors.boardReply.service.BoardReplyService;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Log4j2
public class BoardReplyController {

    private final BoardReplyService replyService;

    @Operation(summary = "Replies POST", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/replies", consumes = MediaType.APPLICATION_JSON_VALUE) // 댓글 등록
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {
        log.info("/replies(POST)...");
        log.info(replyDTO);

        if(bindingResult.hasErrors()){
            throw  new BindException(bindingResult);
        }

        Long rno = replyService.registerReply(replyDTO);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);

        return resultMap;
    }
    
    @Operation(summary = "boardReply", description = "GET 방식으로 게시물의 댓글 목록을 보여줌")
    @GetMapping(value = "/replies/list/{boardNo}") // 특정 게시글의 댓글 목록
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("boardNo") Long boardNo, PageRequestDTO pageRequestDTO){
        log.info("/replies/list/{boardNo}(GET)...");
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getCommentWithReplyList(boardNo, pageRequestDTO);

        return responseDTO;
    }
    
    @Operation(summary = "Read reply", description = "GET 방식으로 특정 댓글 조회")
    @GetMapping(value = "/replies/{rno}") // 댓글 조회
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno){
        log.info("/replies/{rno}(GET)...");
        ReplyDTO replyDTO = replyService.getReply(rno);

        return replyDTO;
    }


    @Operation(summary = "Reply Modify", description = "PUT 방식으로 댓글 수정")
    @PutMapping(value = "/replies/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE) // 댓글 수정
    public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO){
        log.info("/replies/{rno}(PUT)...");

        replyService.modifyReply(replyDTO);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);

        return resultMap;
    }



    @Operation(summary = "Delete reply", description = "Delete 방식으로 댓글 삭제")
    @DeleteMapping(value = "/replies/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno){
        replyService.removeReply(rno);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);

        return resultMap;
    }






}
