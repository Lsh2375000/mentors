package kr.nomadlab.mentors.member.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.nomadlab.mentors.member.domain.MentorApplyVO;
import kr.nomadlab.mentors.member.dto.*;
import kr.nomadlab.mentors.member.service.MemberService;
import kr.nomadlab.mentors.member.service.MenteeService;
import kr.nomadlab.mentors.member.service.MentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;


@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final HttpSession session;

    // 로그인
    @GetMapping("/login")
    public void login(String error, String logout) {
        log.info("login get....");
        log.info(error);
        if (logout != null) {
            log.info("user logout....");
        }
    }

    // 에러페이지
    @GetMapping("/errorPage")
    public void errorPageGET() {
        log.info("errorPage GET...");
    }
    // ---------------------------------------------------- 회원 가입 ----------------------------------------------- //
    // 멘티 회원가입
    @GetMapping("/menteeRegister")
    public void getMentee() {
        log.info("/member/menteeRegister......");
        String isConfirmKey = (String) session.getAttribute("confirmKey");
        log.info("confirmKey: " + isConfirmKey);

    }
    // ----------------------------------------------------
    // 일반 회원 가입 시작
    @PostMapping("/register")
    public String registerPOST(MenteeDTO menteeDTO, MemberDTO memberDTO, HttpServletRequest request, HttpSession session) {
        log.info("/register...");

        String referer = (String)request.getHeader("REFERER"); // 이전의 URL경로를 들고 온다
        log.info("backHistory........." + referer);
        String teeRegisterURL = "http://localhost:8080/member/menteeRegister"; // 멘티 회원가입 주소

        String inputEmail = (String) session.getAttribute("inputEmail");
        String inputNickname = (String) session.getAttribute("inputNickname");

        if (referer.equals(teeRegisterURL)) { // URL : /member/menteeRegister
            memberService.add(memberDTO);
            menteeService.add(menteeDTO);
        } else {
            return "redirect:/member/errorPage";
        }

        return "redirect:/member/login";
    }
    // 일반 회원 가입 끝

    // 소셜 회원 가입 시작
    @GetMapping("/socialRegister")
    public MemberSecurityDTO socialRegisterGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO) {
        log.info("socialRegister GET....");
//        log.info(memberSecurityDTO.getMid());
        return memberSecurityDTO;
    }
    @PostMapping("/socialRegister")
    public String socialRegisterPOST(MenteeDTO menteeDTO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, String passwd) {
        log.info("socialRegister POST.....");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String socialPw = passwordEncoder.encode(passwd);
        menteeDTO.setMemberId(memberSecurityDTO.getMemberId());
        menteeService.add(menteeDTO);

        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(menteeDTO.getMemberId())
                .passwd(socialPw)
                .del(false)
                .social(false)
                .nickname(menteeDTO.getNickname())
                .build();
        memberService.add(memberDTO);

        return "redirect:/member/login";
    }
    // 소셜 회원 가입 끝

    // ----------------------------------------------------
    //멘토 신청
    @PreAuthorize("hasRole('ROLE_MENTEE')")
    @GetMapping("/mentorApply")
    public void MentorApplyGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO) {
        log.info("MentorApplyGET......");
        log.info("getMno() : " + memberSecurityDTO.getMno());
    }

    @PreAuthorize("hasRole('ROLE_MENTEE')")
    @PostMapping("/mentorApply")
    public String MentorApplyPOST(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, List<MultipartFile> files, MentorApplyDTO mentorApplyDTO) {
        log.info("MentorApplyPOST");
        mentorApplyDTO.setMno(memberSecurityDTO.getMno());
        memberService.addMentorApply(mentorApplyDTO, files);
        return "redirect:/";
    }
    // ---------------------------------------------------- 회원 수정 ----------------------------------------------- //
    // ============= 로그인 기능 ============== //
    // 회원 정보 수정 시작
    @PreAuthorize("hasRole('ROLE_MENTOR')")
    @GetMapping("/mentorModify")
    public void mentorModifyGet(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model) {
        log.info("mentor Modify GET...");
        log.info("memberSecurityDTO : " + memberSecurityDTO);
        MemberDTO memberDTO = memberService.getOne(memberSecurityDTO.getMemberId());
        log.info(memberDTO);
        model.addAttribute("memberDTO", memberDTO);
        MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMemberId());
        model.addAttribute("mentorDTO", mentorDTO);

    }
    @PreAuthorize("hasRole('ROLE_MENTEE')")
    @GetMapping("/menteeModify")
    public void menteeModifyGet(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model) {
        log.info("mentee Modify GET...");
        log.info("memberSecurityDTO : " + memberSecurityDTO);
        MemberDTO memberDTO = memberService.getOne(memberSecurityDTO.getMemberId());
        log.info(memberDTO);
        model.addAttribute("memberDTO", memberDTO);
        MenteeDTO menteeDTO = menteeService.getOne(memberSecurityDTO.getMemberId());
        model.addAttribute("menteeDTO", menteeDTO);
    }
    @PreAuthorize("hasRole('ROLE_MENTOR')")
    @PostMapping("/mentorModify")
    public String mentorModifyPost(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, MentorDTO mentorDTO, List<MultipartFile> files) {
        log.info("mentor Modify POST() ....");
        log.info( "닉네임 확인 " + memberSecurityDTO.getNickname());
        log.info("추가하려는 파일 크기: " + files);

        MemberDTO memberDTO = MemberDTO.builder()
                .nickname(memberSecurityDTO.getNickname())
                .memberId(memberSecurityDTO.getMemberId())
                .build();
        memberService.modifyMember(memberDTO);
        mentorDTO.setMemberId(memberSecurityDTO.getMemberId());
        log.info("등록하려는 파일 : " + files);
        mentorService.modify(mentorDTO, files);
        return "redirect:/mypage/mentor";
    }
    @PreAuthorize("hasRole('ROLE_MENTEE')")
    @PostMapping("/menteeModify")
    public String menteeModifyPost(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, MenteeDTO menteeDTO) {
        log.info("mentee Modify POST() ....");
        log.info( "닉네임 확인 " + memberSecurityDTO.getNickname());

        MemberDTO memberDTO = MemberDTO.builder()
                .nickname(memberSecurityDTO.getNickname())
                .memberId(memberSecurityDTO.getMemberId())
                .build();
        memberService.modifyMember(memberDTO);
        menteeDTO.setMemberId(memberSecurityDTO.getMemberId());
        menteeService.modify(menteeDTO);

        return "redirect:/mypage/mentee";
    }
    // 비밀번호 수정
    @PostMapping("/passwordEdit")
    public String  passwordEditPOST(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, String modifyPw) {
        log.info("PasswordEdit POST...");
//        log.info(modifyPw);
        String memberId = memberSecurityDTO.getMemberId();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodeModifyPw = passwordEncoder.encode(modifyPw);
        log.info("이메일 : " + memberId + "새로운 비밀번호 : " + encodeModifyPw);
        memberService.modifyPassword(encodeModifyPw, memberId);
        session.invalidate();
        return "redirect:/member/login";
    }
    // 회원 정보 수정 끝
    // 회원 탈퇴
    @PostMapping("/quit") // 추후 논리적 삭제 기능 추가해서 회원 이메일 30일 정도 보관
    public String quitPOST(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, HttpSession session, String mpwChk) {
        log.info("Quit POST()...");
//        log.info("입력한 비밀번호 : " + mpwChk);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        log.info(memberSecurityDTO.getMid());
        String encodePW = memberSecurityDTO.getPasswd();

        if (passwordEncoder.matches(mpwChk, encodePW)) {

            if (memberSecurityDTO.getAuthorities().toArray()[0].toString().equals("ROLE_MENTOR")) {

                log.info("멘토 회원 탈퇴...");
                mentorService.remove(memberSecurityDTO.getMemberId());
            } else if (memberSecurityDTO.getAuthorities().toArray()[0].toString().equals("ROLE_MENTEE")) {

                log.info("멘티 회원 탈퇴...");
                menteeService.remove(memberSecurityDTO.getMemberId());
            }
            memberService.updateMemberIsDel(memberSecurityDTO.getMemberId());
            session.invalidate(); // 세션 삭제

            return "redirect:/";
        }

        return null;
    }
    // 회원 탈퇴 끝
    // ============= 로그인 기능 끝 ============== //
    // ============= 비로그인 기능 ============== //
    // 비로그인 비밀번호 재설정
    @GetMapping("/forgotPW")
    public void forgotPWGET() {
        log.info("forgotPW GET()....");
    }

    @GetMapping("/resetPassword")
    public String resetPasswordGET(HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("resetPassword GET() ....");
        String email = (String) session.getAttribute("email");
        boolean isEmail = (boolean)session.getAttribute("isEmail");
//        log.info(email);
//        log.info(isEmail);
        if (!isEmail) {
            redirectAttributes.addAttribute("isEmail", isEmail);
            return "redirect:/member/forgotPW";
        }
        return null;
    }
    @PostMapping("/resetPassword")
    public String resetPasswordPOST(HttpSession session, String modifyPw) {
        log.info("resetPassword POST() ....");
        String email = (String) session.getAttribute("email");
        //        log.info("세션 이메일 : " + email);
        //        log.info("새로 사용할 비밀번호 : " + modifyPw);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePw = passwordEncoder.encode(modifyPw);

        memberService.modifyPassword(encodePw, email);

        session.removeAttribute("email");
        session.removeAttribute("isEmail");

        return "redirect:/member/login";
    }
    // 비로그인 비밀번호 재설정 끝



    // --------------------------------- 회원 수정 끝 ----------------------------------------------------------- //

}
