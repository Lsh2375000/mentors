package kr.nomadlab.mentors.payment.mapper;

import kr.nomadlab.mentors.payment.vo.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    void paymentReqInsert(PaymentVO paymentVO);

    void insert(PaymentVO paymentVO);
    PaymentVO findByOrderId(String orderId);

    //toss최종 결과 update
    void update(PaymentVO paymentVO);
}
