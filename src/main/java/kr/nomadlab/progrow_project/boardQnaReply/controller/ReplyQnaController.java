package kr.nomadlab.progrow_project.boardQnaReply.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.nomadlab.progrow_project.boardQnaReply.dto.QReplyDTO;
import kr.nomadlab.progrow_project.boardQnaReply.service.QReplyService;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/qnaReplies")
@Log4j2
@RequiredArgsConstructor // 의존성 주입을 위해
public class ReplyQnaController {
    private final QReplyService qReplyService;

    @Operation(summary = "qnaReplies POST", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody QReplyDTO qReplyDTO, BindingResult bindingResult) throws BindException {
        log.info(qReplyDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException((bindingResult));
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long qnaRno = qReplyService.addReplyQ(qReplyDTO);
        resultMap.put("qnaRno", qnaRno);

        return resultMap;
    }

    @Operation(summary = "qnaReplies of QBoard", description = "GET 방식으로 특정 Qna 게시물의 댓글 목록")
    @GetMapping(value = "/list/{qnaBoardNo}")
    public PageResponseDTO<QReplyDTO> getListQ(@PathVariable("qnaBoardNo") Long qnaBoardNo, @Valid PageRequestDTO pageRequestDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있는 경우, 페이지를 1로 설정 (또는 필요에 따라 다른 기본값으로 설정)
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        // @PathVariable 경로에 있는 값 사용
        PageResponseDTO<QReplyDTO> pageResponseDTO = qReplyService.getListOfBoardQR(qnaBoardNo, pageRequestDTO);

        return pageResponseDTO;
    }

    @Operation(summary = "Read QReply", description = "GET 방식으로 특정 댓글 조회")
    @GetMapping(value = "/{qnaRno}")
    public QReplyDTO getReplyDTO(@PathVariable("qnaRno") Long qnaRno) {
        QReplyDTO qReplyDTO = qReplyService.read(qnaRno);
        return qReplyDTO;
    }

    @Operation(summary = "Delete QReply", description = "DELETE 방식으로 특정 댓글 삭제")
    @DeleteMapping("/{qnaRno}")
    public Map<String, Long> remove(@PathVariable("qnaRno") Long qnaRno) {
        qReplyService.removeOne(qnaRno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("qnaRno", qnaRno);
        return resultMap;
    }

    @Operation(summary = "qnaReplies PUT", description = "PUT 방식으로 댓글 수정")
    @PutMapping(value = "/{qnaRno}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(@PathVariable("qnaRno") Long qnaRno, @RequestBody QReplyDTO qReplyDTO) {
        qReplyDTO.setQnaRno(qnaRno);

        qReplyService.modifyOne(qReplyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        log.info(resultMap);
        resultMap.put("qnaRno", qnaRno);

        return resultMap;
    }

}
