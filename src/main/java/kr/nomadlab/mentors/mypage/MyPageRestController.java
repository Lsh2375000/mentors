package kr.nomadlab.mentors.mypage;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.payment.dto.PaymentDto;
import kr.nomadlab.mentors.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageRestController {
    private final PaymentService paymentService;
    @GetMapping(value = "/paymentsHistory/{mno}")
    public PageResponseDTO<PaymentDto> getList(@PathVariable("mno") Long mno, PageRequestDTO pageRequestDTO) {
        // @PathVariable 경로에 있는 값 사용
//        log.info(pageRequestDTO.getSkip());
        log.info("/list/crbNo---------------"+ mno + pageRequestDTO.getSkip()+ pageRequestDTO.getSize());

        PageResponseDTO<PaymentDto> paymentDto = paymentService.getListPayments(mno, pageRequestDTO);

        return paymentDto;
    }
}
