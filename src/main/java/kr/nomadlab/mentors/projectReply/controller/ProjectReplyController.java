package kr.nomadlab.mentors.projectReply.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.boardReply.dto.ReplyDTO;
import kr.nomadlab.mentors.boardReply.service.BoardReplyService;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.projectReply.dto.ProjectReplyDTO;
import kr.nomadlab.mentors.projectReply.service.ProjectReplyService;
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
public class ProjectReplyController {

    private final ProjectReplyService projectReplyService;

    @Operation(summary = "projectReplies POST", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/projectReplies", consumes = MediaType.APPLICATION_JSON_VALUE) // 댓글 등록
    public Map<String, Long> register(@Valid @RequestBody ProjectReplyDTO projectReplyDTO, BindingResult bindingResult) throws BindException {
        log.info("/projectReplies(POST)...");
        log.info(projectReplyDTO);

        if(bindingResult.hasErrors()){
            throw  new BindException(bindingResult);
        }

        Long pjrNo = projectReplyService.registerReply(projectReplyDTO);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("pjrNo", pjrNo);

        return resultMap;
    }
    
    @Operation(summary = "projectReply", description = "GET 방식으로 게시물의 댓글 목록을 보여줌")
    @GetMapping(value = "/projectReplies/list/{projectNo}") // 특정 게시글의 댓글 목록
    public PageResponseDTO<ProjectReplyDTO> getList(@PathVariable("projectNo") Long projectNo, PageRequestDTO pageRequestDTO){
        log.info("/projectReplies/list/{projectNo}(GET)...");
        PageResponseDTO<ProjectReplyDTO> responseDTO = projectReplyService.getCommentWithReplyList(projectNo, pageRequestDTO);

        return responseDTO;
    }
    
    @Operation(summary = "Read projectReplies", description = "GET 방식으로 특정 댓글 조회")
    @GetMapping(value = "/projectReplies/{pjrNo}") // 댓글 조회
    public ProjectReplyDTO getReplyDTO(@PathVariable("pjrNo") Long pjrNo){
        log.info("/projectReplies/{pjrNo}(GET)...");
        ProjectReplyDTO projectReplyDTO = projectReplyService.getReply(pjrNo);

        return projectReplyDTO;
    }


    @Operation(summary = "Reply Modify", description = "PUT 방식으로 댓글 수정")
    @PutMapping(value = "/projectReplies/{pjrNo}", consumes = MediaType.APPLICATION_JSON_VALUE) // 댓글 수정
    public Map<String, Long> modify(@PathVariable("pjrNo") Long pjrNo, @RequestBody ProjectReplyDTO projectReplyDTO){
        log.info("/projectReplies/{pjrNo}(PUT)...");

        projectReplyService.modifyReply(projectReplyDTO);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("pjrNo", pjrNo);

        return resultMap;
    }



    @Operation(summary = "Delete reply", description = "Delete 방식으로 댓글 삭제")
    @DeleteMapping(value = "/projectReplies/{pjrNo}")
    public Map<String, Long> remove(@PathVariable("pjrNo") Long pjrNo){
        projectReplyService.removeReply(pjrNo);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("pjrNo", pjrNo);

        return resultMap;
    }






}
