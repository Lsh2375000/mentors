package kr.nomadlab.mentors.payment.controller;

import jakarta.validation.Valid;
import kr.nomadlab.mentors.admin.dto.AdminExSearchDTO;
import kr.nomadlab.mentors.admin.service.AdminService;
import kr.nomadlab.mentors.admin.dto.CoinStatsDTO;
import kr.nomadlab.mentors.admin.service.CoinStatsService;
import kr.nomadlab.mentors.exception.CustomLogicException;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.payment.dto.PaymentDto;
import kr.nomadlab.mentors.payment.dto.PaymentFailDto;
import kr.nomadlab.mentors.payment.dto.PaymentSuccessDto;
import kr.nomadlab.mentors.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payments")
@Log4j2
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final CoinStatsService coinStatsService;
    private final AdminService adminService;

    @GetMapping("")
    public String payment(){
        log.info("/payment");
        return "/payments/payment";
    }
    @GetMapping("/success")
    public String success(
            @RequestParam String paymentKey,
            @RequestParam String orderId,
            @RequestParam Long amount,
            Model model,
            @AuthenticationPrincipal MemberSecurityDTO member
            ) throws Exception {
        log.info("/successssssssssss");
        try {
            PaymentSuccessDto paymentSuccessDto = paymentService.tossPaymentSuccess(paymentKey, orderId, amount);
            member.setCoin((int) (member.getCoin()+(amount/1000)));
            log.info(paymentSuccessDto);
            model.addAttribute("payments", paymentSuccessDto);

            // 통계 잡기
            CoinStatsDTO coinStatsDTO = new CoinStatsDTO();
            coinStatsDTO.setAmount(amount/1000);
            coinStatsService.insertCoinStats(coinStatsDTO);
            return "payments/success";
        }
        catch (CustomLogicException e) {
            return "redirect:/payments/fail?code="+e.getExceptionCode()+"&message="+e.getMessage()+"&orderId="+orderId;
        }
    }

    @GetMapping("/fail")
    public String fail(
            @RequestParam("code") String code,
            @RequestParam("message") String message,
            @RequestParam("orderId") String orderId,
            Model model
    ) throws Exception{
        PaymentFailDto paymentFailDto = paymentService.tossPaymentFail(code, message, orderId);
        model.addAttribute("paymentFail", paymentFailDto);

        if(paymentFailDto.getErrorMessage().equals("Payment already approved") && paymentFailDto.getErrorCode().equals("ALREADY_APPROVED")){
            return "payments/successed";
        }

        else {
            return "payments/fail";
        }
    }
}
