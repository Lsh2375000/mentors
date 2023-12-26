package kr.nomadlab.mentors.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.board.dto.BoardLikeDTO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.board.service.BoardService;
import kr.nomadlab.mentors.project.dto.ProjectLikeDTO;
import kr.nomadlab.mentors.project.dto.ProjectTagDTO;
import kr.nomadlab.mentors.project.service.ProjectService;
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
@RequestMapping("/project")
@Log4j2
@RequiredArgsConstructor
public class ProjectRestController {
    private final ProjectService projectService;

    @Operation(summary = "like POST", description = "POST 방식으로 좋아요 추가")
    @PostMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> addLike(@Valid @RequestBody ProjectLikeDTO projectLikeDTO, BindingResult bindingResult) throws BindException {
        log.info("/project/like(POST)....");
        log.info(projectLikeDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException((bindingResult));
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long pjlNo = projectService.addLike(projectLikeDTO);
        log.info(pjlNo);
        resultMap.put("pjlNo", pjlNo);

        return resultMap;
    }

    @Operation(summary = "like DELETE", description = "DELETE 방식으로 좋아요 삭제")
    @DeleteMapping(value = "/like/{pjlNo}")
    public Map<String, Boolean> removeLike(@PathVariable(name = "pjlNo") Long pjlNo) {
        log.info("/project/like/" + pjlNo + "(DELETE)...");

        Map<String, Boolean> resultMap = new HashMap<>();
        Boolean result = projectService.removeLike(pjlNo);
        resultMap.put("result", result);

        return resultMap;
    }

    @Operation(summary = "tag PUT", description = "PUT 방식으로 태그 수정")
    @PutMapping(value = "/tag/{projectNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modifyTag(@RequestBody List<ProjectTagDTO> tagList, @PathVariable(name = "projectNo") Long projectNo) {
        log.info("/project/tag/" + projectNo + "(PUT)...");
        log.info(tagList);

        projectService.modifyHashTag(projectNo, tagList);
        return ResponseEntity.ok("Success");
    }
}
