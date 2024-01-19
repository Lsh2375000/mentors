package kr.nomadlab.mentors.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long payNo;
    private String paymentKey;
    private String payType;
    private Long amount;
    private String orderId;
    private String orderName;
    private Long mno;
    private String memberId;
    private String memberName;
    private String createdAt;
    private String approveAt;
    private String failReason;
    private boolean paySuccessYN;
    private boolean payFailYN;
}
