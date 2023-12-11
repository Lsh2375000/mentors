package kr.nomadlab.progrow_project.member.controller;

import kr.nomadlab.progrow_project.member.dto.MemberJoinDTO;
import kr.nomadlab.progrow_project.member.dto.MemberSecurityDTO;
import kr.nomadlab.progrow_project.member.dto.MenteeDTO;
import kr.nomadlab.progrow_project.member.dto.MentorDTO;
import kr.nomadlab.progrow_project.member.service.MenteeService;
import kr.nomadlab.progrow_project.member.service.MentorService;
import kr.nomadlab.progrow_project.member.service.SMemberService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final SMemberService sMemberService;
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

    /* 로그인 */
    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        /* 에러와 예외를 모델에 담아 view resolve */
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/login";
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
    //멘토 회원가입
    @GetMapping("/mentorRegister")
    public void registerGET() {
        log.info("/member/mentorRegister......");
        String isConfirmKey = (String) session.getAttribute("confirmKey");
        log.info("confirmKey: " + isConfirmKey);
    }
    // ----------------------------------------------------
    // 일반 회원 가입 시작
    @PostMapping("/register")
    public String registerPOST(MentorDTO mentorDTO, MenteeDTO menteeDTO, List<MultipartFile> files, HttpServletRequest request, HttpSession session) {
        log.info("/register...");

        String referer = (String) request.getHeader("REFERER"); // 이전의 URL경로를 들고 온다
        log.info("backHistory........." + referer);

        log.info("여기에 파일 이름찍혀야 함 ㅠㅠ : " + files);
        String torRegisterURL = "http://localhost:8080/member/mentorRegister"; // 멘토 회원가입 주소
        String teeRegisterURL = "http://localhost:8080/member/menteeRegister"; // 멘티 회원가입 주소

        String inputEmail = (String) session.getAttribute("inputEmail");
        String inputNickname = (String) session.getAttribute("inputNickname");

        if (referer.equals(torRegisterURL)) { // URL : /member/mentorRegister

                log.info("TYPE : TOR.....");
                mentorDTO.setType("tor");
                mentorService.add(mentorDTO, files);
                MemberJoinDTO memberJoinDTO = MemberJoinDTO.builder()
                        .mid(mentorDTO.getMentor_id())
                        .mpw(mentorDTO.getPasswd())
                        .del(false)
                        .social(false)
                        .nickname(menteeDTO.getNickname())
                        .type(mentorDTO.getType())
                        .build();
//                log.info(mentorDTO);
                sMemberService.add(memberJoinDTO);

        } else if (referer.equals(teeRegisterURL)) { // URL : /member/menteeRegister
            log.info("TYPE : TEE.....");
            menteeDTO.setType("tee");
            menteeService.add(menteeDTO);
            MemberJoinDTO memberJoinDTO = MemberJoinDTO.builder()
                    .mid(menteeDTO.getMentee_id())
                    .mpw(menteeDTO.getPasswd())
                    .del(false)
                    .social(false)
                    .nickname(mentorDTO.getNickname())
                    .type(menteeDTO.getType())
                    .build();
//            log.info(menteeDTO);
            sMemberService.add(memberJoinDTO);
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
    public String socialRegisterPOST(MenteeDTO menteeDTO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO) {
        log.info("socialRegister POST.....");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String socialPw = passwordEncoder.encode("1111");
        menteeDTO.setMentee_id(memberSecurityDTO.getMid());
        menteeDTO.setPasswd(socialPw);
        menteeService.add(menteeDTO);

        MemberJoinDTO memberJoinDTO = MemberJoinDTO.builder()
                .mid(memberSecurityDTO.getMid())
                .mpw(socialPw)
                .social(true)
                .del(false)
                .type("tee")
                .nickname(menteeDTO.getNickname()).build();
        sMemberService.add(memberJoinDTO);

        return "redirect:/member/login";
    }
    // 소셜 회원 가입 끝

    // ---------------------------------------------------- 회원 수정 ----------------------------------------------- //
    // ============= 로그인 기능 ============== //
    // 회원 정보 수정 시작
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping({"/mentorModify", "/menteeModify"})
    public void memberModifyGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model) {
        log.info("memberModify GET...");
        log.info("memberSecurityDTO : " + memberSecurityDTO);
        String type = memberSecurityDTO.getType();


        if (type.equals("tor")) {
            MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMid());
            model.addAttribute("mentorDTO", mentorDTO);

        } else if (type.equals("tee")) {
            MenteeDTO menteeDTO = menteeService.getOne(memberSecurityDTO.getMid());
            model.addAttribute("menteeDTO", menteeDTO);
        }
    }
    @PostMapping({"/mentorModify", "/menteeModify"})
    public String memberModifyPOST(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, MenteeDTO menteeDTO, MentorDTO mentorDTO, List<MultipartFile> files) {
        log.info("Member Modify POST() ....");
        log.info( "닉네임 확인 " + memberSecurityDTO.getNickname());
        log.info("추가하려는 파일 크기: " + files);
        log.info(mentorService.getOne(memberSecurityDTO.getMid()));
        log.info("이름 : " + mentorDTO.getName());




        MemberJoinDTO memberJoinDTO = MemberJoinDTO.builder()
                .nickname(memberSecurityDTO.getNickname())
                .mid(memberSecurityDTO.getMid())
                .build();
        sMemberService.modifyMember(memberJoinDTO);

        if (memberSecurityDTO.getType().equals("tor")) {
//            mentorDTO = MentorDTO.builder()
//                    .nickname(memberSecurityDTO.getNickname())
//                    .mentor_id(memberSecurityDTO.getMid())
//                    .build();
            mentorDTO.setMentor_id(memberSecurityDTO.getMid());
            log.info("등록하려는 파일 : " + files);
            mentorService.modify(mentorDTO, files);

        } else if (memberSecurityDTO.getType().equals("tee")) {
//            menteeDTO = MenteeDTO.builder()
//                    .nickname(memberSecurityDTO.getNickname())
//                    .mentee_id(memberSecurityDTO.getMid())
//                    .build();
            menteeDTO.setMentee_id(memberSecurityDTO.getMid());
            menteeService.modify(menteeDTO);
        }

        return memberSecurityDTO.getType().equals("tor") ? "redirect:/mypage/mentor" : "redirect:/mypage/mentee";
    }
    // 비밀번호 수정
    @PostMapping("/passwordEdit")
    public String  passwordEditPOST(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, String modifyPw) {
        log.info("PasswordEdit POST...");
//        log.info(modifyPw);
        String mid = memberSecurityDTO.getMid();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodeModifyPw = passwordEncoder.encode(modifyPw);
//        log.info("이메일 : " + mid + "새로운 비밀번호 : " + encodeModifyPw);

        String type = memberSecurityDTO.getType();

        if (type.equals("tor")) {
            sMemberService.modifyPassword(encodeModifyPw, mid);
            mentorService.modifyPw(encodeModifyPw, mid);

        } else if (type.equals("tee")) {
            sMemberService.modifyPassword(encodeModifyPw, mid);
            menteeService.modifyPw(encodeModifyPw, mid);

        }

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
        String encodePW = memberSecurityDTO.getMpw();
        String type = memberSecurityDTO.getType();

            if (passwordEncoder.matches(mpwChk, encodePW)) {

                if (type.equals("tor")) {

                    log.info("멘토 회원 탈퇴...");
                    mentorService.remove(memberSecurityDTO.getMid());
                } else if (type.equals("tee")) {

                    log.info("멘티 회원 탈퇴...");
                    menteeService.remove(memberSecurityDTO.getMid());
                }
                session.invalidate(); // 세션 삭제
                sMemberService.removeMember(memberSecurityDTO.getMid());

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

        String type = sMemberService.getMemberId(email).getType();
//        log.info(type);

        if (type.equals("tor")) {
            mentorService.modifyPw(encodePw, email);
        } else if (type.equals("tee")) {
            menteeService.modifyPw(encodePw, email);
        }
        sMemberService.modifyPassword(encodePw, email);

        session.removeAttribute("email");
        session.removeAttribute("isEmail");

        return "redirect:/member/login";
    }
    // 비로그인 비밀번호 재설정 끝



    // --------------------------------- 회원 수정 끝 ----------------------------------------------------------- //

}
