package kr.nomadlab.mentors.mypage;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.main.dto.MentorReviewDTO;
import kr.nomadlab.mentors.main.service.MentorReviewService;
import kr.nomadlab.mentors.payment.dto.PaymentDTO;
import kr.nomadlab.mentors.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageRestController {
    private final PaymentService paymentService;
    private final MentorReviewService mentorReviewService;

    @GetMapping(value = "/paymentsHistory/{mno}")
    public PageResponseDTO<PaymentDTO> getList(@PathVariable("mno") Long mno, PageRequestDTO pageRequestDTO) {
        // @PathVariable 경로에 있는 값 사용
//        log.info(pageRequestDTO.getSkip());
        log.info("/list/crbNo---------------"+ mno + pageRequestDTO.getSkip()+ pageRequestDTO.getSize());

        PageResponseDTO<PaymentDTO> paymentDTO = paymentService.getListPayments(mno, pageRequestDTO);

        return paymentDTO;
    }

    @GetMapping("/reviewList/{mno}") // 멘토에게 달린 수강평 목록
    public List<MentorReviewDTO> reviewList(@PathVariable("mno")Long mno){
        List<MentorReviewDTO> dtoList = mentorReviewService.mentorReviewList(mno);

        return dtoList;
    }
}
