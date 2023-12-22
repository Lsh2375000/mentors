package kr.nomadlab.mentors.payInfo.mapper;

import kr.nomadlab.mentors.payInfo.vo.PayInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayInfoMapper {
    void insertPayInfo(PayInfoVO payInfoVO);

    List<PayInfoVO> checkDate();

    void updateIsComplete(Long mbNo);
    void coinPayMentoring();
}
