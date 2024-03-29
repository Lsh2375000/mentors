package kr.nomadlab.mentors.payment.controller;

import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.payment.config.TossPaymentConfig;
import kr.nomadlab.mentors.payment.dto.PaymentDTO;
import kr.nomadlab.mentors.payment.dto.PaymentPAO;
import kr.nomadlab.mentors.payment.dto.PaymentResDto;
import kr.nomadlab.mentors.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping({"/payment"})
@RequiredArgsConstructor
public class PaymentRestController {
    private final PaymentService paymentService;

    private final TossPaymentConfig tossPaymentConfig;

    @PostMapping("/toss")
    public ResponseEntity requestTossPayment(@RequestBody PaymentPAO paymentPAO, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){
        //toss 요청 데이터 저장
        log.info("/payment/toss");
        log.info(paymentPAO);
        log.info(memberSecurityDTO);
        PaymentDTO paymentDTO = paymentService.paymentReqRegister(memberSecurityDTO, paymentPAO);
        PaymentResDto paymentResDTO = PaymentResDto.builder()
                .payType(paymentDTO.getPayType())
                .amount(paymentDTO.getAmount())
                .orderId(paymentDTO.getOrderId())
                .orderName(paymentDTO.getOrderName())
                .customerName(paymentDTO.getMemberName())
                .createdAt(paymentDTO.getCreatedAt())
                .failReason(paymentDTO.getFailReason())
                .build();
        paymentResDTO.setSuccessUrl(tossPaymentConfig.getSuccessUrl());
        paymentResDTO.setFailUrl(tossPaymentConfig.getFailUrl());
        return ResponseEntity.ok().body(paymentResDTO);
    }

//    @PostMapping("/confirm")
//    public ResponseEntity tossPaymentSuccess(
//            @RequestParam String paymentKey,
//            @RequestParam String orderId,
//            @RequestParam Long amount
//    ){
//        log.info("payments/success/success");
//        return ResponseEntity.ok().body(paymentService.tossPaymentSuccess(paymentKey, orderId, amount));
//    }
}
