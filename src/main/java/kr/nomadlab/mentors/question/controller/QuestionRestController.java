package kr.nomadlab.mentors.question.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.board.dto.BoardLikeDTO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.board.service.BoardService;
import kr.nomadlab.mentors.question.dto.QNAVoteDTO;
import kr.nomadlab.mentors.question.dto.QuestionTagDTO;
import kr.nomadlab.mentors.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
@Log4j2
@RequiredArgsConstructor
public class QuestionRestController {
    private final QuestionService questionService;

    @Operation(summary = "vote POST", description = "POST 방식으로 좋아요 추가")
    @PostMapping(value = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> addVote(@Valid @RequestBody QNAVoteDTO qnaVoteDTO, BindingResult bindingResult) throws BindException {
        log.info("/question/vote(POST)....");
        log.info(qnaVoteDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException((bindingResult));
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long vno = questionService.addVote(qnaVoteDTO);
        log.info(vno);
        resultMap.put("vno", vno);

        return resultMap;
    }

    @Operation(summary = "vote DELETE", description = "DELETE 방식으로 좋아요 삭제")
    @DeleteMapping(value = "/vote/{vno}")
    public Map<String, Boolean> removeVote(@PathVariable(name = "vno") Long vno) {
        log.info("/question/vote/" + vno + "(DELETE)...");

        Map<String, Boolean> resultMap = new HashMap<>();
        Boolean result = questionService.removeVote(vno);
        resultMap.put("result", result);

        return resultMap;
    }

    @Operation(summary = "tag PUT", description = "PUT 방식으로 태그 수정")
    @PutMapping(value = "/tag/{qno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modifyTag(@RequestBody List<QuestionTagDTO> tagList, @PathVariable(name = "qno") Long qno) {
        log.info("/question/tag/" + qno + "(PUT)...");
        log.info(tagList);

        questionService.modifyQuestionTag(qno, tagList);
        return ResponseEntity.ok("Success");
    }
}
