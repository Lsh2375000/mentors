package kr.nomadlab.mentors.payInfo.mapper;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.payInfo.vo.PayInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayInfoMapper {
    Long insertPayInfo(PayInfoVO payInfoVO);

    List<PayInfoVO> checkDate();

    void updateIsComplete(Long mbNo);

    List<PayInfoVO> getPayInfo(@Param("mno")Long mno, @Param("skip") int skip, @Param("size") int size);

    int getCount(Long mno);

    void returnMenteeCoin(@Param("menteeMno") Long menteeMno,@Param("mbNo") Long mbNo); //멘토링 취소시 결제대기db 삭제

    int getOnePayInfo(@Param("menteeMno") Long menteeMno,@Param("mbNo") Long mbNo); // 환불자 찾아서 코인 환전
}
