package kr.nomadlab.mentors.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFailDto {
    String errorCode;
    String errorMessage;
    String orderId;
}
