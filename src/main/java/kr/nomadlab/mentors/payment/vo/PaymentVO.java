package kr.nomadlab.mentors.payment.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentVO {
    private Long payNo;
    private String payType;
    private String paymentKey;
    private Long amount;
    private String orderId;
    private String orderName;
    private boolean paySuccessYN;
    private Long mno;
    private String memberId;
    private String memberName;
    private String createdAt;
    private String approveAt;
    private String failReason;
    private String cancelReason;
    private boolean payFailYN;
    private boolean cancelYN;

    public void changeSuccessYN(boolean yn){
        this.paySuccessYN = yn;
    }
}
