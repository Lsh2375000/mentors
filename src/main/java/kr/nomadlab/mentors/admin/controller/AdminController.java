package kr.nomadlab.mentors.admin.controller;

import kr.nomadlab.mentors.admin.service.AdminService;
import kr.nomadlab.mentors.member.dto.MentorApplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("")
    public String adminStats(){// 통계 페이지

        return "/admin/stats";
    }

    @GetMapping("/login")
    public void adminLoginGET() {
        log.info("adminLoginGET....");

    }

    @GetMapping("/mentorApply")
    public void mentorApplyGET(Model model) {
        List<MentorApplyDTO> mentorApplyDTO = adminService.getApplyList();
        model.addAttribute("dtoList", mentorApplyDTO);
    }


    @GetMapping("/exchangeList")
    public void exchangeList(){ // 환전 신청 처리 페이지
        
    }

    @GetMapping("/userPage")
    public String userPage(){ // 사용자 페이지 가기
        
        // 어드민 로그아웃 메소드
        
        return "redirect:/main";
    }
}
