package kr.nomadlab.mentors.answer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.answer.dto.AnswerDTO;
import kr.nomadlab.mentors.answer.service.AnswerService;
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
@RequestMapping("/answer")
@Log4j2
public class AnswerController {

    private final AnswerService answerService;

    @Operation(summary = "Answer POST", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE) // 댓글 등록
    public Map<String, Long> register(@Valid @RequestBody AnswerDTO answerDTO, BindingResult bindingResult) throws BindException {
        log.info("/answer(POST)...");
        log.info(answerDTO);

        if(bindingResult.hasErrors()){
            throw  new BindException(bindingResult);
        }

        Long ano = answerService.registerAnswer(answerDTO);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("ano", ano);

        return resultMap;
    }
    
    @Operation(summary = "Answer", description = "GET 방식으로 게시물의 댓글 목록을 보여줌")
    @GetMapping(value = "/list/{qno}") // 특정 게시글의 댓글 목록
    public PageResponseDTO<AnswerDTO> getList(@PathVariable("qno") Long qno, PageRequestDTO pageRequestDTO){
        log.info("/answer/list/{qno}(GET)...");
        PageResponseDTO<AnswerDTO> responseDTO = answerService.getAnswerList(qno, pageRequestDTO);

        return responseDTO;
    }
    
    @Operation(summary = "Read answer", description = "GET 방식으로 특정 댓글 조회")
    @GetMapping(value = "/{ano}") // 댓글 조회
    public AnswerDTO getAnswer(@PathVariable("ano") Long ano){
        log.info("/answer/{ano}(GET)...");
        AnswerDTO answerDTO = answerService.getAnswer(ano);

        return answerDTO;
    }


    @Operation(summary = "Modify Answer", description = "PUT 방식으로 댓글 수정")
    @PutMapping(value = "/{ano}", consumes = MediaType.APPLICATION_JSON_VALUE) // 댓글 수정
    public Map<String, Long> modify(@PathVariable("ano") Long ano, @RequestBody AnswerDTO answerDTO){
        log.info("/answer/{ano}(PUT)...");

        answerService.modifyAnswer(answerDTO);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("ano", ano);

        return resultMap;
    }

    @Operation(summary = "Delete Answer", description = "Delete 방식으로 댓글 삭제")
    @DeleteMapping(value = "/{ano}")
    public Map<String, Long> remove(@PathVariable("ano") Long ano) {
        answerService.removeAnswer(ano);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("ano", ano);

        return resultMap;
    }

    @Operation(summary = "Modify Select", description = "PUT 방식으로 답변 채택")
    @PutMapping(value = "/select/{ano}", consumes = MediaType.APPLICATION_JSON_VALUE) // 댓글 수정
    public Map<String, Long> modifySelect(@PathVariable("ano") Long ano, @RequestBody AnswerDTO answerDTO){
        log.info("/answer/{ano}(PUT)...");

        answerService.modifySelect(answerDTO.getQno(), answerDTO.getAno());

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("ano", ano);

        return resultMap;
    }
}
