package kr.nomadlab.progrow_project.mentoring.controller;



import kr.nomadlab.progrow_project.mentoring.MentorRoomNoticeDTO;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;
import kr.nomadlab.progrow_project.member.dto.MenteeDTO;
import kr.nomadlab.progrow_project.member.dto.MentorDTO;
import kr.nomadlab.progrow_project.mentoring.dto.MentoringDTO;
import kr.nomadlab.progrow_project.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping({"/mentoring", "/"})
@Log4j2
@RequiredArgsConstructor
public class MentoringController {
    private final MentoringService mentoringService;


    @GetMapping("/")
    public String mainBa(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model, Principal principal) {
        PageResponseDTO<MentoringDTO> responseDTO = mentoringService.getList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        log.info(pageRequestDTO);

        if (bindingResult.hasErrors()) { // @Valid 를 이용해서 잘못된 파라미터 값들이 들어오면 page는 1, size는 10으로 고정된 값을 처리
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        PageResponseDTO pageResponseDTO = mentoringService.getList(pageRequestDTO);
        log.info(pageResponseDTO);
        model.addAttribute("responseDTO", pageResponseDTO); // html 파일에서 responseDTO. 으로 받아옴

//        MenteeDTO isMentoring = mentoringService.menteeIsMentoring(mentee_id);
//        model.addAttribute("isMentoring", isMentoring);


        try {
            // 여기부터 멘티가 멘토링 중인지
            String loggedInUsername = principal.getName();
            log.info("loggedInUsername..." + loggedInUsername);
            // 서비스를 통한 특정 컬럼 값 확인
            boolean isMenteeMentoring = mentoringService.isMenteeMentoring(loggedInUsername);
            // 모델에 값 전달
            model.addAttribute("isMenteeMentoring", isMenteeMentoring);

        } catch (Exception e) {
            // 예외 발생 시 처리
            // 예외에 따른 로그 작성 또는 예외 처리 로직 추가
            e.printStackTrace(); // 예외 로그 출력
            // 에러를 모델에 추가하여 뷰로 전달할 수도 있습니다.
            model.addAttribute("error", e.getMessage());
            // 다른 예외 처리 로직을 여기에 추가할 수 있습니다.
        }

        try {
            // 여기부터 멘티가 멘토링 중인지
            String loggedInUsername = principal.getName();
            log.info("loggedInUsername..." + loggedInUsername);
            // 멘토가 멘토링 중인지
            boolean isMentorMentoring = mentoringService.isMentorMentoring(loggedInUsername);
            // 모델에 값 전달
            model.addAttribute("isMentorMentoring", isMentorMentoring);

        } catch (Exception e) {
            // 예외 발생 시 처리
            // 예외에 따른 로그 작성 또는 예외 처리 로직 추가
            e.printStackTrace(); // 예외 로그 출력
            // 에러를 모델에 추가하여 뷰로 전달할 수도 있습니다.
            model.addAttribute("error", e.getMessage());
            // 다른 예외 처리 로직을 여기에 추가할 수 있습니다.
        }
        return "/mentoring/main";
    }

    @GetMapping("/register")
    public void register() {
        log.info("/mentoring/register");
    }

    @PostMapping("/register")
    public String registerPOST(MentoringDTO mentoringDTO, HttpServletRequest request, MentorDTO mentorDTO) {
        log.info("/mentoring/register post...");

        mentoringService.add(mentoringDTO);
        mentoringService.isMentoringMentor(mentorDTO); // isMentoring 을 true 로
        return "redirect:/mentoring/list";
    }

    @GetMapping("/main")
    public void mentorMain(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model, Principal principal) {
        log.info(pageRequestDTO);

        if (bindingResult.hasErrors()) { // @Valid 를 이용해서 잘못된 파라미터 값들이 들어오면 page는 1, size는 10으로 고정된 값을 처리
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        PageResponseDTO pageResponseDTO = mentoringService.getList(pageRequestDTO);
        log.info(pageResponseDTO);
        model.addAttribute("responseDTO", pageResponseDTO); // html 파일에서 responseDTO. 으로 받아옴

//        MenteeDTO isMentoring = mentoringService.menteeIsMentoring(mentee_id);
//        model.addAttribute("isMentoring", isMentoring);


        try {
            // 여기부터 멘티가 멘토링 중인지
            String loggedInUsername = principal.getName();
            log.info("loggedInUsername..." + loggedInUsername);
            // 서비스를 통한 특정 컬럼 값 확인
            boolean isMenteeMentoring = mentoringService.isMenteeMentoring(loggedInUsername);
            // 모델에 값 전달
            model.addAttribute("isMenteeMentoring", isMenteeMentoring);

        } catch (Exception e) {
            // 예외 발생 시 처리
            // 예외에 따른 로그 작성 또는 예외 처리 로직 추가
            e.printStackTrace(); // 예외 로그 출력
            // 에러를 모델에 추가하여 뷰로 전달할 수도 있습니다.
            model.addAttribute("error", e.getMessage());
            // 다른 예외 처리 로직을 여기에 추가할 수 있습니다.
        }

        try {
            // 여기부터 멘티가 멘토링 중인지
            String loggedInUsername = principal.getName();
            log.info("loggedInUsername..." + loggedInUsername);
            // 멘토가 멘토링 중인지
            boolean isMentorMentoring = mentoringService.isMentorMentoring(loggedInUsername);
            // 모델에 값 전달
            model.addAttribute("isMentorMentoring", isMentorMentoring);

        } catch (Exception e) {
            // 예외 발생 시 처리
            // 예외에 따른 로그 작성 또는 예외 처리 로직 추가
            e.printStackTrace(); // 예외 로그 출력
            // 에러를 모델에 추가하여 뷰로 전달할 수도 있습니다.
            model.addAttribute("error", e.getMessage());
            // 다른 예외 처리 로직을 여기에 추가할 수 있습니다.
        }
    }

    @GetMapping("/list")
    public void mentorList(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model, Principal principal) {
        log.info(pageRequestDTO);

        if (bindingResult.hasErrors()) { // @Valid 를 이용해서 잘못된 파라미터 값들이 들어오면 page는 1, size는 10으로 고정된 값을 처리
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        PageResponseDTO pageResponseDTO = mentoringService.getList(pageRequestDTO);
        log.info(pageResponseDTO);
        model.addAttribute("responseDTO", pageResponseDTO); // html 파일에서 responseDTO. 으로 받아옴

//        MenteeDTO isMentoring = mentoringService.menteeIsMentoring(mentee_id);
//        model.addAttribute("isMentoring", isMentoring);


        try {
            // 여기부터 멘티가 멘토링 중인지
            String loggedInUsername = principal.getName();
            log.info("loggedInUsername..." + loggedInUsername);
            // 서비스를 통한 특정 컬럼 값 확인
            boolean isMenteeMentoring = mentoringService.isMenteeMentoring(loggedInUsername);
            // 모델에 값 전달
            model.addAttribute("isMenteeMentoring", isMenteeMentoring);

        } catch (Exception e) {
            // 예외 발생 시 처리
            // 예외에 따른 로그 작성 또는 예외 처리 로직 추가
            e.printStackTrace(); // 예외 로그 출력
            // 에러를 모델에 추가하여 뷰로 전달할 수도 있습니다.
            model.addAttribute("error", e.getMessage());
            // 다른 예외 처리 로직을 여기에 추가할 수 있습니다.
        }

        try {
            // 여기부터 멘티가 멘토링 중인지
            String loggedInUsername = principal.getName();
            log.info("loggedInUsername..." + loggedInUsername);
            // 멘토가 멘토링 중인지
            boolean isMentorMentoring = mentoringService.isMentorMentoring(loggedInUsername);
            // 모델에 값 전달
            model.addAttribute("isMentorMentoring", isMentorMentoring);

        } catch (Exception e) {
            // 예외 발생 시 처리
            // 예외에 따른 로그 작성 또는 예외 처리 로직 추가
            e.printStackTrace(); // 예외 로그 출력
            // 에러를 모델에 추가하여 뷰로 전달할 수도 있습니다.
            model.addAttribute("error", e.getMessage());
            // 다른 예외 처리 로직을 여기에 추가할 수 있습니다.
        }


//        // 여기부터 멘티가 멘토링 중인지
//        String loggedInUsername = principal.getName();
//        // 서비스를 통한 특정 컬럼 값 확인
//        boolean isMenteeMentoring = mentoringService.isMenteeMentoring(loggedInUsername);
//        // 모델에 값 전달
//        model.addAttribute("isMenteeMentoring", isMenteeMentoring);


    }

    @GetMapping("/recruit") // 멘티의 멘토링가입창
    public void recruit(Long mNo, Model model) {
        MentoringDTO mentoringDTO = mentoringService.getOne(mNo);
        log.info("/mentoring/recruit");

        model.addAttribute("dto2", mentoringDTO); // dto라는 이름으로 html 파일에서 사용하게끔

//        MenteeDTO isMentoring = mentoringService.menteeIsMentoring(mentee_id);
//        model.addAttribute("isMentoring", isMentoring);


    }

    @PostMapping("/recruit") // 멘티의 멘토링 가입
    public String recruitPOST(MentoringDTO mentoringDTO, HttpServletRequest request, MenteeDTO menteeDTO) {
        log.info("/mentoring/recruit post...");

        mentoringService.recruitAdd(mentoringDTO); // 멘티의 멘토링 참여
        mentoringService.recruitMentee(menteeDTO); // 멘티의 mentoring 여부 true 로

        return "redirect:/mentoring/list";
    }


    @PostMapping("/modify")
    public String modifyPost(MentoringDTO mentoringDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("has error");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("mNo", mentoringDTO.getMNo());
            return "redirect:/mentoring/modify?";
        }
        mentoringService.modifyOne(mentoringDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("mNo", mentoringDTO.getMNo());
        return "redirect:/mentoring/mentorRoom";
    }

    // 멘토의 멘토링방
    @GetMapping({"/mentorRoom","/modify"})
    public void mentorRoom(MentoringDTO mentoringDTO, Model model, Principal principal) {
        log.info("/mentoring/mentorRoom");

        String loggedInMentorId = principal.getName();
        log.info("loggedInMentorId..." + loggedInMentorId);
        // 서비스를 통한 특정 컬럼 값 확인 - 멘토의 멘토링 정보
        MentoringDTO mentorRoom = mentoringService.mentorRoom(loggedInMentorId);

        // 모델에 값 전달 - - 멘토의 멘토링 정보
        model.addAttribute("mentorRoom", mentorRoom);

        List<MentorRoomNoticeDTO> noticeList = mentoringService.getAllNotice();
        model.addAttribute("noticeList", noticeList);

    }



    @PostMapping("/mentorRoom") // 공지사항 작성
    public String mentorRoomPost(MentorRoomNoticeDTO mentorRoomNoticeDTO) {
        log.info("/mentoring/mentorRoom post...");
        mentoringService.addNotice(mentorRoomNoticeDTO);

        return "redirect:/mentoring/mentorRoom";
    }


    // 멘티의 멘토링방
    @GetMapping("/menteeRoom")
    public void menteeRoom(MentoringDTO mentoringDTO, Model model, Principal principal) {
        log.info("/mentoring/menteeRoom");

        String loggedInMenteeId = principal.getName();
        log.info("loggedInMenteeId..." + loggedInMenteeId);
        // 서비스를 통한 특정 컬럼 값 확인
        MentoringDTO menteeRoom = mentoringService.menteeRoom(loggedInMenteeId);

        // 모델에 값 전달
        model.addAttribute("menteeRoom", menteeRoom);

        List<MentorRoomNoticeDTO> noticeList = mentoringService.getAllNotice();
        model.addAttribute("noticeList", noticeList);
    }


    // 멘토링Room 공지사항 상세
    @GetMapping({"/noticeRead"})
    public void noticeRead(Long num, Model model) {
        MentorRoomNoticeDTO mentorRoomNoticeDTO = mentoringService.getOneNotice(num);
        log.info(mentorRoomNoticeDTO);
        model.addAttribute("dto", mentorRoomNoticeDTO);
    }

    @PostMapping("/noticeModify")
    public String modifyNoticePost(MentorRoomNoticeDTO mentorRoomNoticeDTO, RedirectAttributes redirectAttributes) {
        try {
            log.info("mentorRoomNoticeDTO... " + mentorRoomNoticeDTO);
            mentoringService.modifyNotice(mentorRoomNoticeDTO);
            redirectAttributes.addFlashAttribute("result", "modified");
            redirectAttributes.addAttribute("num", mentorRoomNoticeDTO.getNum());
            return "redirect:/mentoring/noticeRead";
        } catch (Exception e) {
            // 예외가 발생하면 콘솔에 출력하도록 설정
            e.printStackTrace();
            // 예외에 대한 적절한 처리를 수행하세요.
            // 오류 메시지를 로그에 남기거나 사용자에게 적절한 오류 메시지를 보여주는 등의 처리를 수행할 수 있습니다.
//            return "error-page"; // 오류 페이지로 리다이렉트 또는 오류 페이지를 반환하는 등의 처리
            return "redirect:/mentoring/noticeRead";
        }

    }

    @PostMapping("/noticeRemove")
    public String remove(MentorRoomNoticeDTO MentorRoomNoticeDTO, RedirectAttributes redirectAttributes) {
        Long num = MentorRoomNoticeDTO.getNum();
        log.info("noticeRemove post......" + num);
        mentoringService.removeNotice(num);

        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/mentoring/mentorRoom";
    }

}