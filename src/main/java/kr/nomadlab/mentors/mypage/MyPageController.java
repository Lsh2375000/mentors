package kr.nomadlab.mentors.mypage;



import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.exChange.dto.ExchangeDto;
import kr.nomadlab.mentors.exChange.service.ExchangeService;
import kr.nomadlab.mentors.main.dto.MainDTO;
import kr.nomadlab.mentors.main.dto.MentorReviewDTO;
import kr.nomadlab.mentors.main.service.MainService;
import kr.nomadlab.mentors.main.service.MentorReviewService;
import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.dto.MenteeDTO;
import kr.nomadlab.mentors.member.dto.MentorDTO;

import kr.nomadlab.mentors.member.service.MemberService;
import kr.nomadlab.mentors.member.service.MenteeService;
import kr.nomadlab.mentors.member.service.MentorService;
import kr.nomadlab.mentors.payInfo.dto.PayInfoDto;
import kr.nomadlab.mentors.payInfo.service.PayInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;
    private final MentorService mentorService;
    private final MenteeService menteeService;

    private final MentorReviewService mentorReviewService;
    private final MainService mainService;

    private final PayInfoService payInfoService;
    private final ExchangeService exchangeService;

    /*멘토 프로필 시작*/
    @GetMapping("/mentorProfile")
    public void mentorMyPageGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model, String nickname) {
        log.info("mentorProfile GET...");

        /*공통으로 담을 값*/
        MemberDTO memberDTO = memberService.getProfileNickname(nickname);
        log.info("memberDTO : " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        int reviewCnt = mentorReviewService.mentorReviewCount(memberDTO.getMno());
        model.addAttribute("reviewCnt", reviewCnt);

        int memberRole = memberService.getMemberRole(memberDTO.getMemberId());
        model.addAttribute("memberRole", memberRole);
        /*공통으로 담을 값*/


        if (memberSecurityDTO != null) { // 로그인시 가져올 값
            if (memberSecurityDTO.getNickname().equals(nickname)) { // 로그인하고 해당 프로필의 닉네임이 같으면
                MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMemberId());
                log.info("mentorDTO : " + mentorDTO);
                model.addAttribute("mentorDTO", mentorDTO);
            } else { // 로그인하고 해당 프로필의 닉네임이 다르면
                MentorDTO mentorDTO = mentorService.getOne(memberDTO.getMemberId());
                model.addAttribute("mentorDTO", mentorDTO);
            }
        } else if (memberSecurityDTO == null) { // 비로그인시 가져올 값
          MentorDTO mentorDTO = mentorService.getOne(memberDTO.getMemberId());
          model.addAttribute("mentorDTO", mentorDTO);
        }
    }
    /*멘토 프로필 끝*/
    /*멘티 프로필 시작*/
    @GetMapping("/menteeProfile")
    public void menteeMyPageGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model, String nickname) { // 멘티 마이페이지 GET
        log.info("menteeProfile GET...");

        /*공통으로 담을 값*/
        MemberDTO memberDTO = memberService.getProfileNickname(nickname);
        log.info("memberDTO : " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        int reviewCnt = mentorReviewService.menteeReviewCount(memberDTO.getMno());
        model.addAttribute("reviewCnt", reviewCnt);

        int memberRole = memberService.getMemberRole(memberDTO.getMemberId());
        model.addAttribute("memberRole", memberRole);
        /*공통으로 담을 값*/

        if(memberSecurityDTO != null) {
            if (memberSecurityDTO.getNickname().equals(nickname)) {
                MenteeDTO menteeDTO = menteeService.getOne(memberSecurityDTO.getMemberId());
                log.info("menteeDTO : " + menteeDTO);
                model.addAttribute("menteeDTO", menteeDTO);
            } else {
                MenteeDTO menteeDTO = menteeService.getOne(memberDTO.getMemberId());
                model.addAttribute("menteeDTO", menteeDTO);
            }
        } else if (memberSecurityDTO == null) {
            MenteeDTO menteeDTO = menteeService.getOne(memberDTO.getMemberId());
            model.addAttribute("menteeDTO", menteeDTO);
        }

    }

    /*멘티 프로필 끝*/

    /*자기 소개 시작*/
    @GetMapping("/intro")
    public void introduceGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model, String nickname ) {
        log.info("introduceGET() ...");

        MemberDTO memberDTO = memberService.getProfileNickname(nickname);
        log.info("자기소개 memberDTO : " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        int memberRole = memberService.getMemberRole(memberDTO.getMemberId());
        log.info("자기소개 회원 롤 : " + memberRole);
        model.addAttribute("memberRole", memberRole);

        // 마이페이지 자기소개 페이지
        if (memberSecurityDTO != null) { // 로그인 하고 자기 소개 페이지로 갔을 때
            if (memberSecurityDTO.getAuthorities().toArray()[0].toString().equals("ROLE_MENTOR")) {
                log.info("멘토 유저 마이페이지 자기소개 진입");
                /*멘토 로그인*/
                MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMemberId());
                model.addAttribute("mentorDTO", mentorDTO);

                int reviewCnt = mentorReviewService.mentorReviewCount(memberDTO.getMno());
                log.info("멘티 자기소개 수강평 수");
                model.addAttribute("reviewCnt", reviewCnt);

            } else if (memberSecurityDTO.getAuthorities().toArray()[0].toString().equals("ROLE_MENTEE")) {
                log.info("멘티 유저 마이페이지 자기소개 진입");
                /*멘티 로그인*/
                enterMenteePage(model, memberSecurityDTO); // 회원정보, 멘티정보 들고옴
                int reviewCnt = mentorReviewService.menteeReviewCount(memberDTO.getMno());
                log.info("멘티 자기소개 수강평 수");
                model.addAttribute("reviewCnt", reviewCnt);
            }
        } else if (memberSecurityDTO == null){ // 로그인 안하고 유저의 자기소개 들어갔을 때
            log.info("비로그인 유저 프로필 진입");
            anonymousUserEnter(model, nickname);
        }


    }
    /*자기 소개 끝*/




    /* 멘토 메인 영역*/
    @GetMapping("/mainListTor")// 내가 작성한 멘토링 목록 보기
    public void mainList(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, String nickname){
        pageRequestDTO.setSize(12);
        PageResponseDTO<MainDTO> mainList = null;
        MemberDTO memberDTO = memberService.getProfileNickname(nickname);
        log.info("로그인 여부 : " + memberSecurityDTO);

        if(memberSecurityDTO != null){
            log.info("로그인 시");
            if (memberSecurityDTO.getNickname().equals(nickname)) {
                // 멘토 마이페이지에서 멘토링 목록진입
                log.info("멘토 마이페이지 멘토링 목록");
                enterMentorPage(model, memberSecurityDTO);
                mainList = mainService.myPageList(pageRequestDTO, memberSecurityDTO.getMno());

            } else {
                // 다른 멘토의 프로필에서 멘토링 목록진입
                log.info("다른 멘토 프로필의 멘토링 목록");
                enterMentorProfile(model, nickname);
                mainList = mainService.myPageList(pageRequestDTO, memberDTO.getMno());

            }

        } else if (memberSecurityDTO == null){
            log.info("비로그인 멘토 멘토링 목록");
            anonymousUserEnter(model, nickname);
            mainList = mainService.myPageList(pageRequestDTO, memberDTO.getMno());
        }

        log.info("end는 ?"+mainList.getEnd());

        model.addAttribute("nickname", nickname);
        model.addAttribute("mainList", mainList);
        model.addAttribute("sort", pageRequestDTO.getSort());
    }

    @GetMapping("/mainList/remove") // 내가 작성한 멘토링 삭제
    public String removeMain(Long mbNo){
        mainService.removeOne(mbNo);

        return "redirect:/mypage/mainList";
    }

    @GetMapping("/mainList/modify") // 내가 작성한 멘토링 수정페이지
    public String modifyMain(Model model, Long mbNo, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){
        Long mno = memberSecurityDTO.getMno();
        MainDTO mainDTO = mainService.getBoard(mbNo);

        int mentoringCnt = mainService.mentoringCnt(mno);

        enterMentorPage(model, memberSecurityDTO);

        model.addAttribute("mentoringCnt", mentoringCnt);
        model.addAttribute("mainDTO", mainDTO);
        return "/mypage/mainModify";
    }

    @PostMapping("/mainList/modify") // 멘토의 수정하기
    public String modifyMain(MainDTO mainDTO){
        log.info("수정 DTO ? " + mainDTO);
        mainService.modifyBoard(mainDTO);
        return "redirect:/mypage/mainList";
    }

    @GetMapping("/exchange")
    public String exchangeVitamin(@AuthenticationPrincipal MemberSecurityDTO member, PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<PayInfoDto> payInfoDtoList = payInfoService.getPayInfo(member.getMno(), pageRequestDTO);
        model.addAttribute("payInfoDtoList", payInfoDtoList);
        enterMentorPage(model, member);

        return "/mypage/exchange";
    }

    @PostMapping("/bankInfo")
    public String registerBank(@AuthenticationPrincipal MemberSecurityDTO member, ExchangeDto exchangeDto){
        exchangeDto.setMno(member.getMno());
        exchangeDto.setCoin(member.getCoin());
        exchangeDto.setAmount(member.getCoin()*1000);
        exchangeService.insertExchange(exchangeDto);
        PayInfoDto payInfoDto = PayInfoDto.builder()
                .price(member.getCoin())
                .mentorMno(member.getMno())
                .mbNo(0L)
                .build();
        payInfoService.savePayInfo( 0L,payInfoDto); //0은 관리자
        memberService.exchangeCoin(member.getMno());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Authentication 객체의 Principal이 MemberSecurityDTO인지 확인 후 변경
        if (authentication != null && authentication.getPrincipal() instanceof MemberSecurityDTO) {
            MemberSecurityDTO userDetails = (MemberSecurityDTO) authentication.getPrincipal();
            userDetails.setCoin(0);
        }

        return "redirect:/mypage/exchange";
    }
    /* 멘토 메인 영역 끝 */

    /* 멘티 메인 영역*/

    @GetMapping("/mainListTee") // 멘토링 목록
    public void mainListTee(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, String nickname){
        pageRequestDTO.setSize(12);
        PageResponseDTO<MainDTO> mainListTee = null;

        if(memberSecurityDTO != null){ // 로그인 했을때
            mainListTee = mainService.mainListTee(pageRequestDTO, memberSecurityDTO.getMno());
            enterMenteePage(model, memberSecurityDTO);
            boolean isReview = mentorReviewService.isReview(memberSecurityDTO.getMno());
            model.addAttribute("isReview", isReview);
        }else{ // 비로그인 시
            MemberDTO memberDTO = memberService.getProfileNickname(nickname);
            mainListTee = mainService.mainListTee(pageRequestDTO, memberDTO.getMno());
        }

        log.info("서비스 sort ?"+pageRequestDTO.getSort());

        model.addAttribute("mainList", mainListTee);
        model.addAttribute("sort", pageRequestDTO.getSort());
    }

    @PostMapping("/review/write")
    public String write(MentorReviewDTO mentorReviewDTO, PageRequestDTO pageRequestDTO){
        mentorReviewService.register(mentorReviewDTO);
        int page = pageRequestDTO.getPage();
        String sort = pageRequestDTO.getSort();
        return "redirect:/mypage/mainListTee?page="+page+"&size=12&sort="+sort;
    }
    /* 멘티 메인 영역 끝*/
    @GetMapping("/paymentsHistory")
    public String paymentHistory(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model){
        enterMenteePage(model, memberSecurityDTO);

        return "/mypage/paymentsHistory";
    }

    private void enterMentorPage(Model model, MemberSecurityDTO memberSecurityDTO) {
        // 멘토페이지 GetMapping 입장 조건
        MemberDTO memberDTO = memberService.getProfileNickname(memberSecurityDTO.getNickname());
        log.info("memberDTO: " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        int memberRole = memberService.getMemberRole(memberDTO.getMemberId());
        model.addAttribute("memberRole", memberRole);

        MentorDTO mentorDTO = mentorService.getOne(memberSecurityDTO.getMemberId());
        log.info("mentorDTO: " + mentorDTO);
        model.addAttribute("mentorDTO", mentorDTO);
    }

    private void enterMenteePage(Model model, MemberSecurityDTO memberSecurityDTO) {
        // 멘티페이지 GetMapping 입장 조건
        MemberDTO memberDTO = memberService.getProfileNickname(memberSecurityDTO.getNickname());
        log.info("memberDTO: " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        MenteeDTO menteeDTO = menteeService.getOne(memberSecurityDTO.getMemberId());
        log.info("mentorDTO: " + menteeDTO);
        model.addAttribute("mentorDTO", menteeDTO);
    }

    private void enterMentorProfile(Model model, String nickname) {
        // (나를 제외한)멘토 프로필에 들어갈때 해당 멘토의 닉네임을 가져와서 처리
        MemberDTO memberDTO = memberService.getProfileNickname(nickname);
        log.info("memberDTO : " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        MentorDTO mentorDTO = mentorService.getOne(memberDTO.getMemberId());
        log.info("mentorDTO : " + mentorDTO);
        model.addAttribute("mentorDTO", mentorDTO);
    }

    private void anonymousUserEnter(Model model, String nickname) {
        log.info("anonymousUserEnter ....");
        // 비회원이 회원 프로필에 들어갔을때 닉네임을 파라미터로 가져와서 회원정보를 조회함
        MemberDTO memberDTO = memberService.getProfileNickname(nickname);
        log.info("memberDTO : " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);

        int memberRole = memberService.getMemberRole(memberDTO.getMemberId());
        // 해당 회원의 ROLE을 확인
        model.addAttribute("memberRole", memberRole);

        if (memberRole == 0) { // 비회원이 멘토 프로필에 들어갔을 때
            int reviewCnt = mentorReviewService.mentorReviewCount(memberDTO.getMno());
            model.addAttribute("reviewCnt", reviewCnt);
            // 멘토에게 달린 수강평 수와 평균평점

            MentorDTO mentorDTO = mentorService.getOne(memberDTO.getMemberId());
            model.addAttribute("mentorDTO", mentorDTO);
            // 멘토 정보

        } else if (memberRole == 1) { // 비회원이 멘티 프로필에 들어갔을때
            int reviewCnt = mentorReviewService.menteeReviewCount(memberDTO.getMno());
            model.addAttribute("reviewCnt", reviewCnt);
            // 멘티가 작성한 수강평 수

            MenteeDTO menteeDTO = menteeService.getOne(memberDTO.getMemberId());
            model.addAttribute("menteeDTO", menteeDTO);
            // 멘티 정보
        }


    }
}
