package kr.nomadlab.mentors.payment.service;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.payment.dto.*;

public interface PaymentService {
//    void paymentResult ();
    //기본정보 요청
    PaymentDTO paymentReqRegister(MemberSecurityDTO memberSecurityDTO, PaymentPAO paymentPAO);
    //결제 성공
    PaymentSuccessDto tossPaymentSuccess(String paymentKey, String orderId, Long amount);

    PaymentFailDto tossPaymentFail(String code, String message, String orderId);

    PageResponseDTO<PaymentDTO> getListPayments(Long mno, PageRequestDTO pageRequestDTO);
}
