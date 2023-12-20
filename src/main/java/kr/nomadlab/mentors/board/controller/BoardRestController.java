package kr.nomadlab.mentors.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.board.dto.BoardLikeDTO;
import kr.nomadlab.mentors.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardRestController {
    private final BoardService boardService;

    @Operation(summary = "like POST", description = "POST 방식으로 좋아요 추가")
    @PostMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> addLike(@Valid @RequestBody BoardLikeDTO boardLikeDTO, BindingResult bindingResult) throws BindException {
        log.info("/board/like(POST)....");
        log.info(boardLikeDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException((bindingResult));
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long blNo = boardService.addLike(boardLikeDTO);
        log.info(blNo);
        resultMap.put("blNo", blNo);

        return resultMap;
    }

    @Operation(summary = "like DELETE", description = "DELETE 방식으로 좋아요 삭제")
    @DeleteMapping(value = "/like/{blNo}")
    public Map<String, Boolean> removeLike(@PathVariable(name = "blNo") Long blNo) {
        log.info("/board/like/" + blNo + "(DELETE)...");

        Map<String, Boolean> resultMap = new HashMap<>();
        Boolean result = boardService.removeLike(blNo);
        resultMap.put("result", result);

        return resultMap;
    }
}
