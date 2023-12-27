package kr.nomadlab.mentors.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.board.service.BoardService;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.project.dto.ProjectDTO;
import kr.nomadlab.mentors.project.dto.ProjectTagDTO;
import kr.nomadlab.mentors.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/project")
@Log4j2
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){ // 게시글 전체 목록 페이지
        log.info("/project/list(GET)...");
        log.info("keyword: " + pageRequestDTO.getKeyword());
        log.info("hashTag: " + pageRequestDTO.getHashTag());

        // 키워드가 존재하면
        String keyword = pageRequestDTO.getKeyword();
        if (keyword != null && !keyword.isEmpty()) {
            pageRequestDTO.setType("twc");
        }

        PageResponseDTO<ProjectDTO> pageResponseDTO = projectService.getProjectList(pageRequestDTO);
        List<ProjectTagDTO> topTagList = projectService.getTopTagList();

        model.addAttribute("responseDTO", pageResponseDTO);
        model.addAttribute("topTagList", topTagList);
    }

//    @PreAuthorize("isAuthenticated()") //로그인한 시용자만
    @GetMapping("/register")
    public void register(){ // 게시글 등록 페이지
        log.info("/project/register(GET)...");
    }

    @PostMapping("/register")
    public String registerPOST(ProjectDTO projectDTO,
                               @RequestParam("hashTags") String[] hashTags,
                               @AuthenticationPrincipal MemberSecurityDTO member){ // 게시글 등록
        log.info("/project/register(POST)...");
        log.info(projectDTO);

        if (hashTags != null) { // 태그가 존재할 경우
            log.info("Hash Tags: " + Arrays.toString(hashTags));
            List<ProjectTagDTO> tagList = new ArrayList<>();
            Arrays.asList(hashTags).forEach(tag -> tagList.add(ProjectTagDTO.builder()
                    .tagName(tag)
                    .build()));
            projectDTO.setTagList(tagList);
        } else {
            log.info("No Hash Tags provided.");
        }

        projectDTO.setMno(member.getMno());
        
        projectService.registerProject(projectDTO);

        return "redirect:/project/list";
    }

    
//    @PreAuthorize("isAuthenticated()") //로그인한 시용자만
    @GetMapping({"/view", "/modify"}) // 게시글 상세 페이지, 수정 페이지
    public void view(@RequestParam("projectNo") Long projectNo, PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request){
        log.info("projectNo: " + projectNo );
        String requestedUrl  = request.getRequestURI(); // /project/read, //project/modify
        log.info("requestedUrl: " + requestedUrl);

        ProjectDTO projectDTO = null;
        if(requestedUrl.equals("/project/view")){
            projectDTO = projectService.getProject(projectNo, "view"); // 조회수 증가 + read 페이지로 이동
        } else {
            projectDTO = projectService.getProject(projectNo, "modify"); //  modify 페이지로 이동
        }

        model.addAttribute("project", projectDTO);
    }


//    @PreAuthorize("principal.username == #boardDTO.memberId") //로그인 정보와 전달 받은 boardDTO nickname가 같다면 수정 가능
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid ProjectDTO projectDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){ // 게시글 수정
        log.info("/project/modify(POST)...");
        log.info(projectDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors....");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("projectNo", projectDTO.getProjectNo());
            return "redirect:/project/modify?" + link;
        }

        projectService.modifyProject(projectDTO);

        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("projectNo", projectDTO.getProjectNo());

        return "redirect:/project/view";
    }

//    @PreAuthorize("principal.username == #boardDTO.memberId") //로그인 정보와 전달 받은 boardDTO nickname 가 같다면 삭제 가능
    @PostMapping("/remove")
    public String removeOne(ProjectDTO projectDTO, RedirectAttributes redirectAttributes){
        log.info("/project/remove(POST)...");
        Long projectNo = projectDTO.getProjectNo();
        log.info("projectNo: " + projectNo);

        projectService.removeProject(projectNo);

        return "redirect:/project/list";
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
