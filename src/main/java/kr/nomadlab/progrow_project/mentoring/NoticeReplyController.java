package kr.nomadlab.progrow_project.mentoring;


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
@RequestMapping("/noticeReplies")
@Log4j2
public class NoticeReplyController {
    private final NoticeReplyService noticeReplyService;
    //    int a=0;
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE) // JSON으로 처리하는 어노테이션
    public Map<String, Long> register(@Valid @RequestBody NoticeReplyDTO noticeReplyDTO, BindingResult bindingResult)
            throws BindException { // @RequestBody는 JSON 문자열을 ReplyDTO로 변환해 줌
        log.info(noticeReplyDTO);

        if(bindingResult.hasErrors()) {
            throw new BindException((bindingResult));
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long rno = noticeReplyService.register(noticeReplyDTO);

        resultMap.put("rno", rno);

        return resultMap;
    }

    // GET 방식으로 특정 게시물의 댓글 목록
    @GetMapping(value = "/list/{num}") // JSON으로 처리하는 어노테이션
    public PageResponseDTO<NoticeReplyDTO> getList(@PathVariable("num") int num, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<NoticeReplyDTO> responseDTO = noticeReplyService.getList(num, pageRequestDTO);
        return responseDTO;
    }

    // GET 방식으로 특정 댓글 조회
    @GetMapping(value = "/{rno}")
    public NoticeReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {
        NoticeReplyDTO noticeReplyDTO = noticeReplyService.read(rno);
        return noticeReplyDTO;
    }

    // DELETE 방식으로 특정 댓글 삭제
    @DeleteMapping(value = "/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {
        noticeReplyService.remove(rno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }

    // PUT 방식으로 특정 댓글 수정
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody NoticeReplyDTO noticeReplyDTO) {
        noticeReplyDTO.setRno(rno); // 전달된 rno의 DTO로 번호를 일치시킴
        noticeReplyService.modify(noticeReplyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }


}
