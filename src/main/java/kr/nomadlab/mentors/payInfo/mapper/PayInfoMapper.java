package kr.nomadlab.mentors.payInfo.mapper;

import kr.nomadlab.mentors.payInfo.vo.PayInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayInfoMapper {
    void insertPayInfo(PayInfoVO payInfoVO);
}
