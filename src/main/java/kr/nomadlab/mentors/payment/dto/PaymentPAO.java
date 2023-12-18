package kr.nomadlab.mentors.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPAO {
    private String payType; //지불 방법
    private Long amount; //가격
    private String orderName; //주문상품명

    private String yourSuccessUrl; //성공 시 리다이렉트 될 URL
    private String yourFailUrl; //실패 시 리다이렉트 될 URL
}
