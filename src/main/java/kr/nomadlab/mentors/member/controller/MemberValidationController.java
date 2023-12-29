package kr.nomadlab.mentors.member.controller;

import jakarta.servlet.http.HttpSession;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.service.MailSenderService;
import kr.nomadlab.mentors.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberValidationController {
//    private final MemberService sMemberService;
    private final MailSenderService mailSenderService;

    private final MemberService memberService;

    // 인증문자 전송
    @GetMapping("/sendConfirmMail")
    @ResponseBody
    public String sendConfirmMail(@RequestParam("mailTo") String mailTo, HttpSession session) throws Exception {
        if (mailSenderService.sendMailByAddMember(mailTo)) {
            String confirmKey = mailSenderService.getConfirmKey(); // 인증문자를 변수에 저장
            session.setAttribute("confirmKey", confirmKey); // 변수를 세션에 저장
            session.setAttribute("inputEmail", mailTo); // 입력한 이메일을 세션에 저장
            log.info("입력한 이메일 : "+ mailTo);
            log.info("인증문자 :" + confirmKey); // 변수를 로그에 출력

            return "true";
        }
        else {
            return "false";
        }
    }

    // 입력한 인증문자 일치 여부 확인
    @PostMapping("/matchConfirmKey")
    @ResponseBody
    public String  matchConfirmKey(@RequestParam("confirmKey") String confirmKey, HttpSession session) throws Exception {
        log.info("matchConfirmKey......");
        String matchConfirmKey =  (String) session.getAttribute("confirmKey"); // 변수에 세션값을 저장
        log.info(matchConfirmKey);
        log.info(confirmKey);
        if (confirmKey.equals(matchConfirmKey)) {
            session.setAttribute("isEmail", true); // 맞는 인증문자를 입력했다면 true를 세션에 담아준다.
            return "true";
        } else {
            return "false";
        }
    }

    // 회원 가입 약관동의
    @GetMapping("/agreement")
    public void memberAgree() {
        log.info("/member/agreement");
    }

    // 비밀번호 재설정시 이메일 존재 유무
    @PostMapping("/isEmail")
    @ResponseBody
    public String isEmail(@RequestParam("email") String email, HttpSession session) {
        log.info("idCheck......");
        log.info(email);

        if (memberService.getMemberId(email) != null) {
            log.info("null.....");
            session.setAttribute("email", email);
            return "ture";
        }
        return "false";
    }

    // 이메일 중복 체크
    @PostMapping("/idCheck")
    @ResponseBody
    public String idCheck(@RequestParam("memberId") String memberId, HttpSession session) {
        log.info("idCheck......");
        log.info(memberId);
        if (memberService.getMemberId(memberId) == null) {
            log.info("null.....");
            return "true";
        }
        return "false";
    }

    // 닉네임 중복 체크
    @PostMapping("/nicknameCheck")
    @ResponseBody
    public String nicknameCheck(@RequestParam("nickname") String nickname, HttpSession session) {
        log.info("nicknameCheck......");
        log.info("입력한 닉네임 : " + nickname);
        session.setAttribute("inputNickname", nickname); // 닉네임을 세션에 담는다
        if (memberService.getMemberNickname(nickname) == null) {
            log.info("null.....");
            return "true";
        }
        return "false";
    }

    // 비밀번호 수정시 현재 비밀번호 체크
    @PostMapping("/passwordCheck")
    @ResponseBody
    public String passwordCheck(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, @RequestParam String inputPw) {
        log.info("passwordCheck...");
        log.info(memberSecurityDTO);
        String encodePw = memberSecurityDTO.getPasswd();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(inputPw, encodePw)) {
            return "true";
        }
        return "false";
    }

    // 회원가입전 아이디 닉네임 마지막 유효성 체크
    @PostMapping("/lastCheck")
    @ResponseBody
    public String lastCheck(HttpSession session, String inputEmail, String inputNickname) {

        String okInputEmail = (String) session.getAttribute("inputEmail");
        String okInputNickname = (String) session.getAttribute("inputNickname");

        log.info("중복 검사로 확인된 값 : " + okInputNickname + ", " + okInputEmail);
        log.info("마지막에 버튼 눌렀을 때 확인 된 값 : " + inputEmail + ", " + inputNickname);

        if (!okInputEmail.equals(inputEmail) ||
                memberService.getMemberId(inputEmail ) != null) {
            // 1. 중복검사할 때 이메일 값과 회원가입 눌렀을 때 이메일 값이 다르거나
            // 2. 회원가입 버튼눌렀을 때 입력된 이메일의 정보가 이미 존재한다면

            log.info("이메일 중복검사 재실행!!");
            session.removeAttribute("inputEmail");
            // 세션에 담긴 값과 입력한값이 다르면
            return "emailFalse";

        } else if (!okInputNickname.equals(inputNickname) ||
                memberService.getMemberNickname(inputNickname) != null) {
            // 1. 중복검사할 때 닉네임 값과 회원가입 눌렀을 때 이메일 값이 다르거나
            // 2. 회원가입 버튼눌렀을 때 입력된 닉네임의 정보가 이미 존재한다면

            log.info("닉네임 중복 검사 재실행!!");
            session.removeAttribute("inputNickname");
            return "nicknameFalse";
        } else if (okInputEmail == null || okInputNickname == null) {
            log.info("이메일 또는 닉네임 입력값 없음");
            return "false";
        }


        return "true";
    }

    // 회원 수정전 닉네임 마지막 유효성 체크
    @PostMapping("/lastModifyCheck")
    @ResponseBody
    public String lastModifyCheck(HttpSession session, String inputNickname) {
        String okInputNickname = (String) session.getAttribute("inputNickname");

        log.info("중복 검사로 확인된 값 : " + okInputNickname);
        log.info("마지막에 버튼 눌렀을 때 확인 된 값 : " + inputNickname);

        if (!okInputNickname.equals(inputNickname) ||
                memberService.getMemberNickname(inputNickname) != null) {
            log.info("닉네임 중복 검사 재실행!!");
            return "false";
        }
        return "ture";
    }
}
