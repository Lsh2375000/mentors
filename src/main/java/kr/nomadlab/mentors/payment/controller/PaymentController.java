package kr.nomadlab.mentors.payment.controller;

import kr.nomadlab.mentors.exception.CustomLogicException;
import kr.nomadlab.mentors.payment.dto.PaymentFailDto;
import kr.nomadlab.mentors.payment.dto.PaymentSuccessDto;
import kr.nomadlab.mentors.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payments")
@Log4j2
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

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
            Model model
    ) throws Exception {
        log.info("/successssssssssss");
        try {
            PaymentSuccessDto paymentSuccessDto = paymentService.tossPaymentSuccess(paymentKey, orderId, amount);

            log.info(paymentSuccessDto);
            model.addAttribute("payments", paymentSuccessDto);

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
