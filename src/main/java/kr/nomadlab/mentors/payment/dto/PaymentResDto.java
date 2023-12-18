package kr.nomadlab.mentors.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResDto {
    //응답으로 올 Dto
    //PaymentDto로 받은 정보들을 검증 후, 실제 결제 요청을 하기 위해 필요한 값을 포함
    private String payType; //결제 타입 - 카드/현금/포인트
    private Long amount; //가격 정보
    private String orderName; //상품명
    private String orderId; //상품번호
    private String customerName; //고객 이름
    private String successUrl; //성공 시 리다이렉트 될 URL
    private String failUrl; //실패 시 리다이렉트 될 URL

    private String failReason; //실패 이유
    private boolean cancelYN; // 취소 YN
    private String cancelReason; //취소 이유
    private String createdAt; //결제가 이루어진 시간
}
