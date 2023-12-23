package kr.nomadlab.mentors.exChange.mapper;

import kr.nomadlab.mentors.exChange.vo.ExchangeVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExchangeMapper {
    void insertExchange (ExchangeVO exchangeVO);
}
