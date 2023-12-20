package kr.nomadlab.mentors.payment.mapper;

import kr.nomadlab.mentors.payment.vo.PaymentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
    void paymentReqInsert(PaymentVO paymentVO);
    void insert(PaymentVO paymentVO);
    PaymentVO findByOrderId(String orderId);
    //toss최종 결과 update
    void update(PaymentVO paymentVO);

    int getCount(Long mno);
    //결제 내역 출력
    List<PaymentVO> selectList(@Param("mno") Long mno, @Param("skip") int skip, @Param("size") int size); //리스트 출력
}
